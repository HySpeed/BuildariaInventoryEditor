package com.fb.bie.view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import com.fb.bie.Data;
import com.fb.bie.model.Model;
import com.fb.bie.view.GUI;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// MenuActions *****************************************************************
public class MenuActions extends JMenu {
  private static final long serialVersionUID = 8275810142110131516L;
  private int  _mnemonic;
  
  
  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  // MenuActions ***************************************************************
  public MenuActions( String name,
                      int    mnemonic ) {
    super( name );
    _mnemonic = mnemonic;
    initGUI();
  } // MenuActions -------------------------------------------------------------
  
  
  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // addToolBarChoices +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void addToolBarChoices( JToolBar toolBar ) {
    
    toolBar.add( buttonActionsAddInven       );
    toolBar.add( buttonActionsDelInven       );
    toolBar.add( buttonActionsRenInven       );
    toolBar.addSeparator();
    toolBar.add( buttonActionsBeforeInven    );
    toolBar.add( buttonActionsAfterInven     );
    toolBar.addSeparator();
    toolBar.add( buttonActionsClearCurInven  );
    toolBar.add( buttonActionsRemoveAllInven );
    
  } // addToolBarChoices -------------------------------------------------------
  
  
  // setControlsEnabled ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setControlsEnabled( boolean enabled ) {
    
    menuActionsDelInven.setEnabled(         enabled );
    buttonActionsDelInven.setEnabled(       enabled );
    menuActionsRenInven.setEnabled(         enabled );
    buttonActionsRenInven.setEnabled(       enabled );
    menuActionsBeforeInven.setEnabled(      enabled );
    buttonActionsBeforeInven.setEnabled(    enabled );
    menuActionsAfterInven.setEnabled(       enabled );
    buttonActionsAfterInven.setEnabled(     enabled );
    menuActionsClearCurInven.setEnabled(    enabled );
    buttonActionsClearCurInven.setEnabled(  enabled );
    menuActionsRemoveAllInven.setEnabled(   enabled );
    buttonActionsRemoveAllInven.setEnabled( enabled );
    
  } // setControlsEnabled ------------------------------------------------------
  
  
  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/

  
  /*****************************************************************************
  * Events                                                                     *
  *****************************************************************************/
  // eventMenuActionsPrevInven ++++++++++++++++++++++++++++++++++++++++++++++++++
  public void eventMenuActionsPrevInven() {
    Data.getView().prevPanel();
  } // eventMenuActionsPrevInven ------------------------------------------------

  
  // eventMenuActionsNextInven ++++++++++++++++++++++++++++++++++++++++++++++++++
  public void eventMenuActionsNextInven() {
    Data.getView().nextPanel();
  } // eventMenuActionsNextInven ------------------------------------------------

  
  // eventMenuActionsAddInven ++++++++++++++++++++++++++++++++++++++++++++++++++
  public void eventMenuActionsAddInven() {
    Data.getView().addInven();
  } // eventMenuActionsAddInven ------------------------------------------------

  
  // eventMenuActionsDelInven ++++++++++++++++++++++++++++++++++++++++++++++++++
  public void eventMenuActionsDelInven() {
    int  proceed = JOptionPane.YES_OPTION;

    proceed = JOptionPane.showConfirmDialog( Data.getView(), 
                                             Data.CONFIRM_DEL_MSG, 
                                             Data.CONFIRM_TITLE, 
                                             JOptionPane.YES_NO_OPTION );

    if ( proceed == JOptionPane.YES_OPTION ) {
      Data.getView().delInven();
    } // if
    
  } // eventMenuActionsDelInven ------------------------------------------------

  
  // eventMenuActionsRenInven ++++++++++++++++++++++++++++++++++++++++++++++++++
  public void eventMenuActionsRenInven() {
    Data.getView().renInven();
  } // eventMenuActionsRenInven ------------------------------------------------

  
  // eventMenuActionsBeforeInven +++++++++++++++++++++++++++++++++++++++++++++++
  public void eventMenuActionsBeforeInven() {
    Data.getView().beforeInven();
  } // eventMenuActionsBeforeInven ---------------------------------------------

  
  // eventMenuActionsAfterInven ++++++++++++++++++++++++++++++++++++++++++++++++
  public void eventMenuActionsAfterInven() {
    Data.getView().afterInven();
  } // eventMenuActionsAfterInven ----------------------------------------------

  
  // eventMenuActionsClearCurInven +++++++++++++++++++++++++++++++++++++++++++++
  public void eventMenuActionsClearCurInven() {
    int  proceed = JOptionPane.YES_OPTION;

    proceed = JOptionPane.showConfirmDialog( Data.getView(), 
                                             Data.CONFIRM_CLR_MSG, 
                                             Data.CONFIRM_TITLE, 
                                             JOptionPane.YES_NO_OPTION );

    if ( proceed == JOptionPane.YES_OPTION ) {
      Data.getView().clearCurInven();
    } // if
    
  } // eventMenuActionsClearCurInven -------------------------------------------


  // eventMenuActionsRemoveAllInven ++++++++++++++++++++++++++++++++++++++++++++
  public void eventMenuActionsRemoveAllInven() {
    int  proceed = JOptionPane.YES_OPTION;

    proceed = JOptionPane.showConfirmDialog( Data.getView(), 
                                             Data.CONFIRM_REM_MSG, 
                                             Data.CONFIRM_TITLE, 
                                             JOptionPane.YES_NO_OPTION );

    if ( proceed == JOptionPane.YES_OPTION ) {
      Data.getView().removeAllInven();
    } // if
    
  } // eventMenuActionsRemoveAllInven ------------------------------------------


  /*****************************************************************************
  * GUI Methods                                                                *
  *****************************************************************************/
  // initGUI +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void initGUI() {
    this.setFont(     GUI.fontSsP14 );
    this.setMnemonic( _mnemonic );

    // attributes ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    menuActionsPrevInven        = setupMenuItem(   Data.MENU_TXT_ACTIONS_PREV_INVEN, 
                                                   Data.MENU_TIP_ACTIONS_PREV_INVEN,
                                                   KeyEvent.VK_A, KeyEvent.VK_A,
                                                   listenerMenuActionsPrevInven, true );
    menuActionsNextInven        = setupMenuItem(   Data.MENU_TXT_ACTIONS_NEXT_INVEN, 
                                                   Data.MENU_TIP_ACTIONS_PREV_INVEN,
                                                   KeyEvent.VK_D, KeyEvent.VK_D,
                                                   listenerMenuActionsNextInven, true );
    menuActionsAddInven         = setupMenuItem(   Data.MENU_TXT_ACTIONS_ADD_INVEN, 
                                                   Data.MENU_TIP_ACTIONS_ADD_INVEN,
                                                   KeyEvent.VK_A, 0,
                                                   listenerMenuActionsAddInven, true );
    buttonActionsAddInven       = setupMenuButton( GUI.ACTIONS_ADD_INVEN,              
                                                   Data.MENU_TIP_ACTIONS_ADD_INVEN,
                                                   listenerMenuActionsAddInven, true );
    menuActionsDelInven         = setupMenuItem(   Data.MENU_TXT_ACTIONS_DEL_INVEN, 
                                                   Data.MENU_TIP_ACTIONS_DEL_INVEN,
                                                   KeyEvent.VK_D, 0,
                                                   listenerMenuActionsDelInven, true );
    buttonActionsDelInven       = setupMenuButton( GUI.ACTIONS_DEL_INVEN,
                                                   Data.MENU_TIP_ACTIONS_DEL_INVEN,
                                                   listenerMenuActionsDelInven, true );
    menuActionsRenInven         = setupMenuItem(   Data.MENU_TXT_ACTIONS_REN_INVEN, 
                                                   Data.MENU_TIP_ACTIONS_REN_INVEN,
                                                   KeyEvent.VK_R, 0,
                                                   listenerMenuActionsRenInven, true );
    buttonActionsRenInven       = setupMenuButton( GUI.ACTIONS_REN_INVEN,
                                                   Data.MENU_TIP_ACTIONS_REN_INVEN,
                                                   listenerMenuActionsRenInven, true );
    menuActionsBeforeInven      = setupMenuItem(   Data.MENU_TXT_ACTIONS_BEFORE_INVEN,
                                                   Data.MENU_TIP_ACTIONS_BEFORE_INVEN,
                                                   KeyEvent.VK_B, 0,
                                                   listenerMenuActionsAheadInven, true );
    buttonActionsBeforeInven    = setupMenuButton( GUI.ACTIONS_BEFORE_INVEN,              
                                                   Data.MENU_TIP_ACTIONS_BEFORE_INVEN,
                                                   listenerMenuActionsAheadInven, true );
    menuActionsAfterInven       = setupMenuItem(   Data.MENU_TXT_ACTIONS_AFTER_INVEN,
                                                   Data.MENU_TIP_ACTIONS_AFTER_INVEN,
                                                   KeyEvent.VK_F, 0,
                                                   listenerMenuActionsBackInven, true );
    buttonActionsAfterInven     = setupMenuButton( GUI.ACTIONS_AFTER_INVEN,
                                                   Data.MENU_TIP_ACTIONS_AFTER_INVEN,
                                                   listenerMenuActionsBackInven, true );
    menuActionsClearCurInven    = setupMenuItem(   Data.MENU_TXT_ACTIONS_CLEAR_CUR_INVEN, 
                                                   Data.MENU_TIP_ACTIONS_CLEAR_CUR_INVEN, 
                                                   KeyEvent.VK_U, 0, 
                                                   listenerMenuActionsClearCurInven, true );
    buttonActionsClearCurInven  = setupMenuButton( GUI.ACTIONS_CLEAR_CUR_INVEN,              
                                                   Data.MENU_TIP_ACTIONS_CLEAR_CUR_INVEN, 
                                                   listenerMenuActionsClearCurInven, true );
    menuActionsRemoveAllInven   = setupMenuItem(   Data.MENU_TXT_ACTIONS_REMOVE_ALL_INVEN, 
                                                   Data.MENU_TIP_ACTIONS_REMOVE_ALL_INVEN, 
                                                   KeyEvent.VK_L, 0, 
                                                   listenerMenuActionsRemoveAllInven, true );
    buttonActionsRemoveAllInven = setupMenuButton( GUI.ACTIONS_REMOVE_ALL_INVEN,              
                                                   Data.MENU_TIP_ACTIONS_REMOVE_ALL_INVEN, 
                                                   listenerMenuActionsRemoveAllInven, true );
      
    // adds ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    this.add( menuActionsPrevInven     );
    this.add( menuActionsNextInven     );
    this.add( menuActionsAddInven      );
    this.add( menuActionsDelInven      );
    this.add( menuActionsRenInven      );
    this.add( menuActionsBeforeInven   );
    this.add( menuActionsAfterInven    );
    this.add( menuActionsClearCurInven );
    this.add( menuActionsRemoveAllInven );
      
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
         JMenuItem   menuActionsPrevInven;    
         JMenuItem   menuActionsNextInven;    
         JMenuItem   menuActionsAddInven;    
  static JButton   buttonActionsAddInven;
         JMenuItem   menuActionsDelInven;  
  static JButton   buttonActionsDelInven;
         JMenuItem   menuActionsBeforeInven;   
  static JButton   buttonActionsBeforeInven;
         JMenuItem   menuActionsAfterInven;   
  static JButton   buttonActionsAfterInven;
         JMenuItem   menuActionsRenInven;   
  static JButton   buttonActionsRenInven;
         JMenuItem   menuActionsClearCurInven;  
  static JButton   buttonActionsClearCurInven;
         JMenuItem   menuActionsRemoveAllInven;  
  static JButton   buttonActionsRemoveAllInven;

    
  /*****************************************************************************
  * Listeners                                                                  *
  *****************************************************************************/
  ActionListener listenerMenuActionsPrevInven = new ActionListener()  { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuActionsPrevInven();   }};

  ActionListener listenerMenuActionsNextInven = new ActionListener()  { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuActionsNextInven();   }};
    
  ActionListener listenerMenuActionsAddInven = new ActionListener()  { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuActionsAddInven();   }};
  
  ActionListener listenerMenuActionsDelInven = new ActionListener()  { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuActionsDelInven();   }};
    
  ActionListener listenerMenuActionsAheadInven = new ActionListener()  { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuActionsBeforeInven();   }};
      
  ActionListener listenerMenuActionsBackInven = new ActionListener()  { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuActionsAfterInven();   }};
        
  ActionListener listenerMenuActionsRenInven = new ActionListener()  { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuActionsRenInven();   }};
          
  ActionListener listenerMenuActionsClearCurInven = new ActionListener()  { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuActionsClearCurInven(); }};

  ActionListener listenerMenuActionsRemoveAllInven = new ActionListener()  { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuActionsRemoveAllInven(); }};
  

} // MenuActions ===============================================================


/** ============================================================================
Copyright (c) fishBowl softWare Inc. All rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
