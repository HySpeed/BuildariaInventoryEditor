package com.fb.bie.view.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenuBar;
import javax.swing.JToolBar;

import com.fb.bie.Data;
import com.fb.bie.view.GUI;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// Menu ************************************************************************
public class Menu extends JMenuBar {
  private static final long serialVersionUID = 5256580400881105605L;

  
  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
	// Menu ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * This class defines the Menu Bar and Tool Bar: 
   * + File
   * + + Open   (toolbar) (always enabled)
   * + + Save   (toolbar) (always enabled)
   * + + SaveAs (toolbar) (always enabled)
   * + + Exit
   * + Actions
   * + + Add Inventory Page    (toolbar) (always enabled)
   * + + Del Inventory Page    (toolbar) (tabs > 0)
   * + + Move Before           (toolbar) (tabs > 0)
   * + + Move After            (toolbar) (tabs > 0)
   * + + Rename Inventory Page (toolbar) (tabs > 0)
   * + + Clear All Inventory   (toolbar) (tabs > 0)
   * + Help
   * + + ReadMe
   * + + About
   */
  public Menu() {
    initGUI();
  } // Menu --------------------------------------------------------------------
  
  
  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // getMenuActions ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public MenuActions getMenuActions() {
    return menuActions;
  } // getMenuActions ----------------------------------------------------------

  
  // getMenuActions ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public MenuFile getMenuFile() {
    return menuFile;
  } // getMenuActions ----------------------------------------------------------

  
  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/

  
  /*****************************************************************************
  * Events                                                                     *
  *****************************************************************************/
  // setControlsEnabled ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setControlsEnabled( boolean enabled ) {
    menuFile.setControlsEnabled(    enabled );
    menuActions.setControlsEnabled( enabled );
  } // setControlsEnabled ------------------------------------------------------

  
  /*****************************************************************************
  * GUI Methods                                                                *
  *****************************************************************************/
  // initGUI +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void initGUI()  {

    // attributes ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    menuFile    = new MenuFile(    Data.MENU_TXT_FILE,    KeyEvent.VK_F );
    menuActions = new MenuActions( Data.MENU_TXT_ACTIONS, KeyEvent.VK_A );
    menuHelp    = new MenuHelp(    Data.MENU_TXT_HELP,    KeyEvent.VK_H );

    toolBar = new JToolBar( "TPE ToolBar" );
    toolBar.setBorder( GUI.borderEtched );
    toolBar.setFloatable( false );
    toolBar.setRollover(  true  );
    toolBar.setOpaque(    false );

    // adds ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    this.add( menuFile    );
    this.add( menuActions );
    this.add( menuHelp    );

    this.add( toolBar );
      toolBar.addSeparator();
      MenuFile.addToolBarChoices( toolBar );
      toolBar.addSeparator();
      MenuActions.addToolBarChoices( toolBar );
      toolBar.addSeparator();

  } // initGUI -----------------------------------------------------------------
  

  /*****************************************************************************
  * GUI Components                                                             *
  *****************************************************************************/
  MenuFile     menuFile;
  MenuActions  menuActions;  
  MenuHelp     menuHelp;

  JToolBar toolBar;


  /*****************************************************************************
  * Listeners                                                                  *
  *****************************************************************************/


} // Menu ======================================================================


/** ============================================================================
Copyright (c) fishBowl softWare Inc. All rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
