package com.fb.bie.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;

import com.fb.bie.BuildariaInventoryEditor;
import com.fb.bie.Data;
import com.fb.bie.model.Model;
import com.fb.bie.model.data.InventoryObjectDAO;
import com.fb.bie.view.GUI;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// MenuFile ********************************************************************
public class MenuFile extends JMenu {
  private static final long serialVersionUID = -7992108824878929045L;
  private int _mnemonic;
  
  
  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  // MenuFile ******************************************************************
  public MenuFile( String name,
                   int    mnemonic ) {
    super( name );
    _mnemonic = mnemonic;
    initGUI();    
  } // MenuFile ----------------------------------------------------------------
  
  
  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // addToolBarChoices +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void addToolBarChoices( JToolBar toolBar ) {
    
    toolBar.add( buttonFileOpen   );
    toolBar.add( buttonFileSave   );
    toolBar.add( buttonFileSaveAs );
    
  } // addToolBarChoices -------------------------------------------------------

  
  // setControlsEnabled ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setControlsEnabled( boolean enabled ) {
    
    menuFileSave.setEnabled(     enabled );
    buttonFileSave.setEnabled(   enabled );
    menuFileSaveAs.setEnabled(   enabled );
    buttonFileSaveAs.setEnabled( enabled );
    
  } // setControlsEnabled ------------------------------------------------------
  
  
  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/
  // openFile ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void openFile( File file ) {

    try {
      Data.setInventoryFile( file );
      Data.getView().setupTitle( file.getName() );
      InventoryObjectDAO.setInventoryObject( Model.loadInventory( file ) );
      Data.setInventoryLoading( true ); // prevents "Inventory Changed" from events during load
      Data.getView().populateFields();
      setControlsEnabled( true );
      Data.setInventoryLoading( false );
      Data.setInventoryClean();
    } // try
    catch ( IOException error ) {
      
      if ( file.getName().equals( Data.INVENTORY_FILE ) ) { 
        JOptionPane.showMessageDialog( Data.getView(), 
                                       "I/O Error: " + error.getMessage() + Data.ERROR_RUNONCE,
                                       "I/O Error: " + Data.APP_TITLE,
                                       JOptionPane.ERROR_MESSAGE );
        BuildariaInventoryEditor.throwError();
      } // if
      else {
        JOptionPane.showMessageDialog( Data.getView(),
                                       "File Error: " + error.getMessage(),
                                       "File Error: " + Data.APP_TITLE,
                                       JOptionPane.ERROR_MESSAGE );
      } // else
      
    } // catch
    catch ( JAXBException error ) {
      JOptionPane.showMessageDialog( Data.getView(), 
                                     "Invalid XML Error: " + error.getMessage(),
                                     "Invalid XML Error: " + Data.APP_TITLE,
                                     JOptionPane.ERROR_MESSAGE );
      BuildariaInventoryEditor.throwError();
    } // catch

  } // openFile ----------------------------------------------------------------

  
  // saveFile ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void saveFile() {
    
    try {
      Model.saveInventory( Data.getInventoryFile() );
      JOptionPane.showMessageDialog( Data.getView(), 
                                     "Inventory saved to " + Data.getInventoryFile().getName(),
                                     Data.APP_TITLE, JOptionPane.INFORMATION_MESSAGE );
    } // try
    catch ( IOException error ) {
      JOptionPane.showMessageDialog( Data.getView(), 
                                     "File Error: " + error.getMessage(),
                                     "File Error: " + Data.APP_TITLE,
                                     JOptionPane.ERROR_MESSAGE );
    } // catch
    catch ( JAXBException error ) {
      JOptionPane.showMessageDialog( Data.getView(), 
                                     "Invalid XML Error: " + error.getMessage(),
                                     "Invalid XML Error: " + Data.APP_TITLE,
                                     JOptionPane.ERROR_MESSAGE );
      BuildariaInventoryEditor.throwError();
    } // catch

      
  } // saveFile ----------------------------------------------------------------

  
  /*****************************************************************************
  * Events                                                                     *
  *****************************************************************************/
  // eventMenuFileOpen +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void eventMenuFileOpen() {
    int  proceed = JOptionPane.YES_OPTION;
    JFileChooser chooser;
    int  result;
    File selectedFile;
    
    if ( Data.getInventoryChanged() ) {
      proceed = JOptionPane.showConfirmDialog( Data.getView(), 
                                               Data.CHANGED_MSG, 
                                               Data.CONFIRM_TITLE, 
                                               JOptionPane.YES_NO_OPTION );
    } // if
    
    if ( proceed == JOptionPane.YES_OPTION ) {
      chooser = new JFileChooser();
      chooser.setFileFilter( new FileNameExtensionFilter( "Inventory files", "xml" ) );
      chooser.setCurrentDirectory( new File( Data.BUILDARIA_DIR ) );

      result = chooser.showOpenDialog( Data.getView() );
      if ( result == JFileChooser.APPROVE_OPTION ) {
        selectedFile = chooser.getSelectedFile();
        openFile( selectedFile );
      } // if
    } // if

  } // eventMenuFileOpen -------------------------------------------------------

  
  // eventMenuFileSave +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void eventMenuFileSave() {

    if ( Data.getInventoryFile() != null ) {
      Data.getView().updateInventory(); // cascades to all panels
      saveFile();
    } // if
    
    Data.setInventoryClean();

  } // eventMenuFileSave -------------------------------------------------------


  // eventMenuFileSaveAs +++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void eventMenuFileSaveAs() {
    int    overwrite = JOptionPane.YES_OPTION;
    JFileChooser chooser;
    int    result;
    File   selectedFile;
    String fileName;

    if ( Data.getInventoryFile() != null ) {

      chooser = new JFileChooser();
      chooser.setFileFilter( new FileNameExtensionFilter( "Inventory files", "xml" ) );
      chooser.setCurrentDirectory( new File( Data.BUILDARIA_DIR ) );

      result = chooser.showSaveDialog( Data.getView() );

      if ( result == JFileChooser.APPROVE_OPTION ) {
        selectedFile = chooser.getSelectedFile();
        fileName = selectedFile.toString();
        if ( !fileName.endsWith( ".xml" ) ) { // if .xml was not included, add it to filename
          fileName = selectedFile.getPath() + ".xml";
        } // if
        selectedFile = new File( fileName );

        if ( selectedFile.exists() ) {
          overwrite = JOptionPane.showConfirmDialog( Data.getView(), 
                                                     Data.OVERWRITE_MSG, 
                                                     Data.CONFIRM_TITLE, 
                                                     JOptionPane.YES_NO_OPTION );
        } // if

        if ( overwrite == JOptionPane.YES_OPTION ) {
          Data.setInventoryFile( selectedFile );
          Data.getView().setupTitle( selectedFile.getName() );
          Data.getView().updateInventory(); 
          saveFile();
          Data.setInventoryClean();
        } // if
      } // if
      
    } // if

  } // eventMenuFileSaveAs -----------------------------------------------------


  // eventMenuFileExit +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public boolean eventMenuFileExit() {
    return BuildariaInventoryEditor.exitApp();
  } // eventMenuFileExit -------------------------------------------------------
  
  
  /*****************************************************************************
  * GUI Methods                                                                *
  *****************************************************************************/
  // initGUI +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void initGUI() {
    this.setFont(     GUI.fontSsP14 );
    this.setMnemonic( _mnemonic );

    // attributes ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    menuFileOpen     = setupMenuItem(   Data.MENU_TXT_FILE_OPEN,   
                                        Data.MENU_TIP_FILE_OPEN, 
                                        KeyEvent.VK_O, KeyEvent.VK_O, 
                                        listenerMenuFileOpen, true );
    buttonFileOpen   = setupMenuButton( GUI.ACTIONS_OPEN,             
                                        Data.MENU_TIP_FILE_OPEN, 
                                        listenerMenuFileOpen, true );
    menuFileSave     = setupMenuItem(   Data.MENU_TXT_FILE_SAVE,   
                                        Data.MENU_TIP_FILE_SAVE, 
                                        KeyEvent.VK_S, KeyEvent.VK_S, 
                                        listenerMenuFileSave, true );
    buttonFileSave   = setupMenuButton( GUI.ACTIONS_SAVE,             
                                        Data.MENU_TIP_FILE_SAVE, 
                                        listenerMenuFileSave, true );
    menuFileSaveAs   = setupMenuItem(   Data.MENU_TXT_FILE_SAVEAS, 
                                        Data.MENU_TIP_FILE_SAVEAS, 
                                        KeyEvent.VK_V, KeyEvent.VK_V, 
                                        listenerMenuFileSaveAs, true );
    buttonFileSaveAs = setupMenuButton( GUI.ACTIONS_SAVEAS,           
                                        Data.MENU_TIP_FILE_SAVEAS, 
                                        listenerMenuFileSaveAs, true );
    menuFileExit     = setupMenuItem(   Data.MENU_TXT_FILE_EXIT,   
                                        Data.MENU_TIP_FILE_EXIT,
                                        KeyEvent.VK_X, KeyEvent.VK_X,
                                        listenerMenuFileExit, true );
          
    // adds ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
   this.add( menuFileOpen   );
   this.add( menuFileSave   );
   this.add( menuFileSaveAs );
   this.add( menuFileExit   );
      
  } // initGUI -----------------------------------------------------------------
  
  
  // setupMenuItem +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private JMenuItem setupMenuItem( String         menuName, 
                                   String         toolTip, 
                                   int            mnemonic, 
                                   int            keyEvent, 
                                   ActionListener listener,
                                   boolean        enabled  ) {
    JMenuItem result = new JMenuItem();  
    
    result.setText( menuName );
    result.setToolTipText( toolTip );
    result.setFont( GUI.fontSsP14 );
    result.setMnemonic( mnemonic );
    if ( keyEvent != 0 ) {
      result.setAccelerator( KeyStroke.getKeyStroke( keyEvent, ActionEvent.CTRL_MASK ) );
    } // if
    result.setEnabled( enabled );
    result.addActionListener( listener );
    
    return result;
  } // setupMenuItem -----------------------------------------------------------
  

  // setupMenuButton +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private JButton setupMenuButton( String         imageName, 
                                   String         toolTip, 
                                   ActionListener listener,
                                   boolean        enabled  ) {
    JButton result = new JButton();  
    
    result.setBorder( GUI.borderEmpty2px );
    result.setContentAreaFilled( false );
    result.setFocusPainted(      false );
    result.setIcon( Model.loadImageIcon( imageName + ".png" ) );
    result.setRolloverEnabled( true );
    result.setRolloverIcon( Model.loadImageIcon( imageName + "Roll.png" ));
    result.setToolTipText(    toolTip );
    result.setEnabled( enabled );
    result.addActionListener( listener );
    
    return result;
  } // setupMenuButton ---------------------------------------------------------

  
  /*****************************************************************************
  * GUI Components                                                             *
  *****************************************************************************/
         JMenuItem   menuFileOpen;    
         JMenuItem   menuFileSave;    
  static JButton   buttonFileOpen;
         JMenuItem   menuFileSaveAs;  
  static JButton   buttonFileSave;
         JMenuItem   menuFileExit;    
  static JButton   buttonFileSaveAs;
  
  
  /*****************************************************************************
  * Listeners                                                                  *
  *****************************************************************************/
  ActionListener listenerMenuFileOpen = new ActionListener()  { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuFileOpen();       }};
  
  ActionListener listenerMenuFileSave = new ActionListener()  { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuFileSave();       }};
  
  ActionListener listenerMenuFileSaveAs = new ActionListener()  { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuFileSaveAs();     }};
  
  ActionListener listenerMenuFileExit = new ActionListener()  { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuFileExit();       }};

    
} // MenuFile ==================================================================


/** ============================================================================
Copyright (c) fishBowl softWare Inc. All rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
