package com.fb.bie.view.menu;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import com.fb.bie.BuildariaInventoryEditor;
import com.fb.bie.Data;
import com.fb.bie.view.GUI;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// MenuHelp ********************************************************************
public class MenuHelp extends JMenu {
  private static final long serialVersionUID = -8365833457036173415L;
  private int _mnemonic;

  
  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  // MenuHelp ******************************************************************
  public MenuHelp( String name,
                   int    mnemonic ) {
    super( name );
    _mnemonic = mnemonic;
    initGUI();
  } // MenuHelp ----------------------------------------------------------------
  
  
  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/

  
  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/

  
  /*****************************************************************************
  * Events                                                                     *
  *****************************************************************************/
  // eventMenuHelpReadMe +++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void eventMenuHelpReadMe() {
    InputStream       stream = null;
    InputStreamReader reader = null;
    String    inputFile;
    JTextArea textArea;
    
    inputFile = Data.README_FILE;
    textArea  = new JTextArea( 20, 41 );
    textArea.setEditable( false );
    textArea.setMargin( new Insets( 2, 2, 2, 2 ) );

    try {
      stream = getClass().getClassLoader().getResourceAsStream( inputFile );
      reader = new InputStreamReader( stream );
      textArea.read( reader, null );
      JOptionPane.showMessageDialog( Data.getView(), 
                                     new JScrollPane( textArea ), 
                                     Data.APP_TITLE + " Read Me", 
                                     JOptionPane.INFORMATION_MESSAGE,
                                     Data.getView().getAppImage() );
    } // try
    catch ( IOException error ) {
      JOptionPane.showMessageDialog( Data.getView(), 
                                     "I/O Error: " + inputFile + ": " + error.getMessage(),
                                     "I/O Error: " + Data.APP_TITLE,
                                     JOptionPane.ERROR_MESSAGE );
    } // catch
    catch ( NullPointerException error ) { // InputStreamReader throws this if file not found
      JOptionPane.showMessageDialog( Data.getView(), 
                                     "I/O Error: " + inputFile + ": " + error.getMessage(),
                                     "I/O Error: " + Data.APP_TITLE,
                                     JOptionPane.ERROR_MESSAGE );
      BuildariaInventoryEditor.throwError();
    } // catch

  } // eventMenuHelpReadMe -----------------------------------------------------


  // eventMenuHelpAbout ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void eventMenuHelpAbout() {
    String message = Data.APP_TITLE + "\n" +
                     "Copyright (c) to proper owners\n" +
                     "Buildaria by wraithx2 and septor\n" +
                     "User interface by _Jon\n";
    JOptionPane.showMessageDialog( Data.getView(), message, Data.APP_TITLE,
                                   JOptionPane.INFORMATION_MESSAGE );
  } // eventMenuHelpAbout ------------------------------------------------------


  /*****************************************************************************
  * GUI Methods                                                                *
  *****************************************************************************/
  // initGUI +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void initGUI() {
    this.setFont(     GUI.fontSsP14 );
    this.setMnemonic( _mnemonic );

    // attributes ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    
    menuHelpReadMe = setupMenuItem( Data.MENU_TXT_HELP_README, 
                                    Data.MENU_TIP_HELP_README, 
                                    KeyEvent.VK_R, 0, 
                                    listenerMenuHelpReadMe, true );
    menuHelpAbout  = setupMenuItem( Data.MENU_TXT_HELP_ABOUT,  
                                    Data.MENU_TIP_HELP_ABOUT,  
                                    KeyEvent.VK_A, 0, 
                                    listenerMenuHelpAbout,  true );
      
    // adds ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    this.add( menuHelpReadMe );
    this.add( menuHelpAbout  );
      
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
  

  /*****************************************************************************
  * GUI Components                                                             *
  *****************************************************************************/
  JMenuItem menuHelpReadMe;
  JMenuItem menuHelpAbout;

    
  /*****************************************************************************
  * Listeners                                                                  *
  *****************************************************************************/
  ActionListener listenerMenuHelpReadMe = new ActionListener() { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuHelpReadMe();    }};
  
  ActionListener listenerMenuHelpAbout = new ActionListener() { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuHelpAbout();     }};

    
} // MenuHelp ==================================================================


/** ============================================================================
Copyright (c) fishBowl softWare Inc. All rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
