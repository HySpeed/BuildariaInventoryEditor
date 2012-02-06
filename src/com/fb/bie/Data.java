package com.fb.bie;

import java.io.File;

import com.fb.bie.model.Model;
import com.fb.bie.view.View;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// Data ########################################################################
public class Data {
  /*****************************************************************************
  * Constants                                                                  *
  *****************************************************************************/
  public  static final String   APP_NAME       = "Buildaria Inventory Editor";
  public  static final String   APP_VER        = "1.02";
  public  static final String   APP_ICON       = "res/icon/BuildariaInventoryEditor.png";
  public  static final String   APP_TITLE      = APP_NAME + " (v" + APP_VER + ")";
  
  public  static final String   BUILDARIA_DIR  = ".";
  public  static final String   INVENTORY_FILE = "Inventories.xml";
  public  static final String   README_FILE    = "res/text/ReadMe.txt";

  public  static final String   ITEM_FILE      = "res/text/ItemList.csv";
  public  static final String   IMAGE_ROOT     = "res/images";
  public  static final int      NO_SELECTION   = -1;
  public  static final String   NO_ITEM_NAME   = "No Item";

  public  static final String   LIBRARY_ERROR   = "Missing class! Is the lib folder present?\n";
  public  static final String   CONFIRM_TITLE   = "Continue?";
  public  static final String   CHANGED_MSG     = "File is changed!\nDo you want to continue?";
  public  static final String   OVERWRITE_MSG   = "File exists!\nDo you want to overwrite?";
  public  static final String   CONFIRM_DEL_MSG = "This will delete these items!\nDo you want to continue?";
  public  static final String   CONFIRM_CLR_MSG = "This will clear this inventory!\nDo you want to continue?";
  public  static final String   CONFIRM_REM_MSG = "This will remove *all* inventories!\nDo you want to continue?";
  public  static final String   NO_CARROT_TITLE = "Carrot Not Available";
  public  static final String   NO_CARROT       = "This item, the Carrot, is a Collector's Edition only item.\n" +
                                                    "You may not add it here.";
  public  static final String   TRASH_SLOT_TITLE = "Trash Slot";
  public  static final String   TRASH_SLOT       = "This slot is reserved for Buildaria trash items";

  public  static final String   WIKI_URL     = "http://wiki.terrariaonline.com/";
  public  static final String   WIKI_PREFIX  = "Prefix";

  public  static final int      XML_ITEMS    = 53; // <InventoryItem> per <Items>
  public  static final int      INVEN_SLOTS  = 40;
  public  static final int      COIN_SLOTS   =  4;
  public  static final int      ARMOR_SLOTS  =  3;
  public  static final int      ACCESS_SLOTS =  5;

  public  static final String   MENU_TXT_FILE        = "File";
  public  static final String   MENU_TXT_FILE_OPEN   = "Open";
  public  static final String   MENU_TIP_FILE_OPEN   = "Open an existing Inventory";
  public  static final String   MENU_TXT_FILE_SAVE   = "Save";
  public  static final String   MENU_TIP_FILE_SAVE   = "Save the current Inventory over the current name";
  public  static final String   MENU_TXT_FILE_SAVEAS = "Save As ...";
  public  static final String   MENU_TIP_FILE_SAVEAS = "Save the current Inventory with a new file name";
  public  static final String   MENU_TXT_FILE_EXIT   = "Exit";
  public  static final String   MENU_TIP_FILE_EXIT   = "Exit " + APP_NAME;

  public  static final String   MENU_TXT_ACTIONS              = "Actions";
  public  static final String   MENU_TXT_ACTIONS_PREV_INVEN   = "Previous Inventory Page";
  public  static final String   MENU_TIP_ACTIONS_PREV_INVEN   = "Change to Previous Inventory Page";
  public  static final String   MENU_TXT_ACTIONS_NEXT_INVEN   = "Next Inventory Page";
  public  static final String   MENU_TIP_ACTIONS_NEXT_INVEN   = "Change to Next Inventory Page";
  public  static final String   MENU_TXT_ACTIONS_ADD_INVEN    = "Add Inventory Panel";
  public  static final String   MENU_TIP_ACTIONS_ADD_INVEN    = "Add empty panel to end";
  public  static final String   MENU_TXT_ACTIONS_DEL_INVEN    = "Delete Inventory Panel";
  public  static final String   MENU_TIP_ACTIONS_DEL_INVEN    = "Delete current panel and all its items";
  public  static final String   MENU_TXT_ACTIONS_REN_INVEN    = "Rename Panel";
  public  static final String   MENU_TIP_ACTIONS_REN_INVEN    = "Rename Current panel";
  public  static final String   MENU_TXT_ACTIONS_BEFORE_INVEN = "Move Inventory Panel Before";
  public  static final String   MENU_TIP_ACTIONS_BEFORE_INVEN = "Move current panel before previous panel";
  public  static final String   MENU_TXT_ACTIONS_AFTER_INVEN  = "Move Inventory Panel After";
  public  static final String   MENU_TIP_ACTIONS_AFTER_INVEN  = "Move current panel after next panel";
  public  static final String   MENU_TXT_ACTIONS_CLEAR_CUR_INVEN = "Clear Current Inventory Panel";
  public  static final String   MENU_TIP_ACTIONS_CLEAR_CUR_INVEN = "Delete Current Inventory Panel Items";
  public  static final String   MENU_TXT_ACTIONS_REMOVE_ALL_INVEN = "Remove All Inventories";
  public  static final String   MENU_TIP_ACTIONS_REMOVE_ALL_INVEN = "Delete All Inventories";

  public  static final String   MENU_TXT_HELP        = "Help";
  public  static final String   MENU_TXT_HELP_README = "Read Me";
  public  static final String   MENU_TIP_HELP_README = "Display the Read Me file";
  public  static final String   MENU_TXT_HELP_ABOUT  = "About";
  public  static final String   MENU_TIP_HELP_ABOUT  = "Show the About information";

  public  static final String   ERROR_RUNONCE        = "\nBuildaria must be run once to create Inventories.xml.";

  private static       int      _splashProgress   = 0;
  private static       Model    _model = null;
  private static       View     _view  = null;
  private static       File     _inventoryFile    = null;
  private static       boolean  _inventoryChanged = false;
  private static       boolean  _inventoryLoading = false;


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // getModel ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static Model getModel() {
    return _model;
  } // getModel ----------------------------------------------------------------
  // setModel ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void setModel( Model model ) {
    _model = model;
  } // setModel ----------------------------------------------------------------


  // getSplashProgress +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static int getSplashProgress() {
    return _splashProgress;
  } // getSplashProgress -------------------------------------------------------
  // setSplashProgress +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void setSplashProgress( int splashProgress ) {
    _splashProgress = splashProgress;
  } // setSplashProgress -------------------------------------------------------
  // incSplashProgress +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static int incSplashProgress( int amount ) {
    return (_splashProgress += amount);
  } // incSplashProgress -------------------------------------------------------


  // getInventoryFile ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static File getInventoryFile() {
    return _inventoryFile;
  } // getInventoryFile --------------------------------------------------------
  // setInventoryFile ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void setInventoryFile( File inventoryFile ) {
    _inventoryFile = inventoryFile;
  } // setInventoryFile --------------------------------------------------------


  // getInventoryChanged +++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static boolean getInventoryChanged() {
    return _inventoryChanged;
  } // getInventoryChanged -----------------------------------------------------
  // setInventoryDirty +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * This is called when a change is made to the inventory.
   * A condition of "_inventoryLoading" is checked to ensure the process of
   *  loading the inventory does not trigger setting "changed" to true.
   * The title is updated to reflect a changed inventory ("*" is added).
   */
  public static void setInventoryDirty() {
    if ( !_inventoryLoading ) { // skip if loading
      _inventoryChanged = true;
      getView().updateTitle( _inventoryChanged );
    } // if
  } // setInventoryClean -------------------------------------------------------
  public static void setInventoryClean() {
    _inventoryChanged = false;
    getView().updateTitle( _inventoryChanged );
  } // setCharacterClean -------------------------------------------------------


  // getInventoryLoading +++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * This flag is used when updating the UI during a load from disk.
   * Populating certain fields (drop downs, edit fields, etc.) will fire change
   *  events.  When this flag is true, those events are ignored.
   */
  public static boolean getInventoryLoading() {
    return _inventoryLoading;
  } // getInventoryLoading -----------------------------------------------------
  // setInventoryLoading +++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * This flag is used when updating the UI during a load from disk.
   * Populating certain fields (drop downs, edit fields, etc.) will fire change
   *  events.  When this flag is true, those events are ignored.
   */
  public static void setInventoryLoading( boolean inventoryLoading ) {
    _inventoryLoading = inventoryLoading;
  } // setInventoryLoading -----------------------------------------------------


  // getView +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static View getView() {
    return _view;
  } // getView -----------------------------------------------------------------
  // setView +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void setView( View view ) {
    _view = view;
  } // setView -----------------------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/
  // getIntegerValue +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * Convenience method to convert a JTextField value to an integer.
   * Number Format Exceptions are ignored, so the result may be zero (0).
   */
  public static int getIntegerValue( String text ) {
    int result = 0;

    text = ( text.isEmpty() ) ? "0" : text;
    try {
      result = Integer.parseInt( text );
    } // try
    catch ( NumberFormatException ignored ) {} // catch

    return result;
  } // getIntegerValue ---------------------------------------------------------


} // Data ======================================================================


/** ============================================================================
Some portions Copyright (c) fishBowl softWare Inc. Some rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
