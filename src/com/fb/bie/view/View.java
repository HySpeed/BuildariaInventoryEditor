package com.fb.bie.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.fb.bie.BuildariaInventoryEditor;
import com.fb.bie.Data;
import com.fb.bie.model.data.InventoryItemObject;
import com.fb.bie.model.data.InventoryListObject;
import com.fb.bie.model.data.InventoryObject;
import com.fb.bie.model.data.InventoryObjectDAO;
import com.fb.bie.view.menu.Menu;
import com.fb.bie.view.panel.InventoryPanel;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// View ************************************************************************
public class View extends JFrame {
  private static final long serialVersionUID = 3313848400117561494L;
  private int             _currentPanel = 0;
  private InventoryObject _inventoryObjects;


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  // View **********************************************************************
  /**
   * This class defines the User Interface.  All UI elements descend from here.
   * Before this class is instantiated, 
   * Model must be called so data elements are available.
   * All UI elements are defined global to this class for event handling purposes.
   */
  public View() {
    super( Data.APP_TITLE );

    UIManager.put( "ToolTip.background", GUI.colorPopupBack );
    UIManager.put( "ToolTip.font",       GUI.fontSsP14 );

    initGUI();

  } // View --------------------------------------------------------------------


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // getAppImage +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public ImageIcon getAppImage() {
    return new ImageIcon( imageApp );
  } // getAppImage -------------------------------------------------------------


  // getMenu +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public Menu getMenu() {
    return menuBar;
  } // getMenu -----------------------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/
  // populateFields ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * This method is called as part of initial setup and after a file load.
   * Reset selected panel to Blank Inventory.
   * Cascades populateFields to panels.
   * populateFields methods pull data from the structures of the inventory file
   *  and populate the UI elements.
   */
  public void populateFields() {

    tpContent.removeAll();
    processInventory();
    _currentPanel = 0;
    tpContent.setSelectedIndex( _currentPanel );
    tpContent.setEnabled( true );

  } // populateFields ----------------------------------------------------------


  // processInventory ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void processInventory() {
    ArrayList<InventoryListObject> inventoryLists;
    Iterator<InventoryListObject>  inventoryListObjectsIter;
    InventoryListObject            inventoryListObject;
    ArrayList<InventoryItemObject> inventoryItemList;
    String                         listName;
    InventoryPanel                 inventoryPanel;

    _inventoryObjects = InventoryObjectDAO.getInventoryObject();
    if ( _inventoryObjects != null ) {

      inventoryLists = _inventoryObjects.getInventorysLists();
      inventoryListObjectsIter = inventoryLists.iterator();
      while ( inventoryListObjectsIter.hasNext() ) {

        inventoryListObject = (InventoryListObject) inventoryListObjectsIter.next();
        if ( inventoryListObject != null ) {

          listName = inventoryListObject.getListName();
          //System.out.println( "List Name: " + listName ); // DEV ListName
          inventoryItemList = inventoryListObject.getInventorysItems();
          inventoryPanel    = new InventoryPanel( listName, inventoryItemList );
          tpContent.addTab( listName, null, inventoryPanel );
          inventoryPanel.processInventory();

        } // if

      } // while

    } // if

  } // processInventory --------------------------------------------------------


  // updateInventory +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * This panel is called as part of a save operation
   * Cascades updateInventory to panels.
   * updateInventory methods pull data from the UI and put it in the structures of
   *  the inventory output file.
   */
  public void updateInventory() {
    int index;
    InventoryPanel inventoryPanel;

    for ( index = 0; index < tpContent.getTabCount(); index++ ) {
      inventoryPanel = (InventoryPanel) tpContent.getComponentAt( index );
      inventoryPanel.updateInventory();
    } // for

  } // updateInventory ---------------------------------------------------------


  // setupTitle ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * Set the application title back to APP_NAME, APP_VER, and CharName.
   * This method is used by populateFields() and updatePlayer() in CharacterPanel
   * - not used by CharName changed - only when change is saved
   */
  public void setupTitle( String name ) {
    String title;

    title = Data.APP_TITLE + " [" + name + "]";
    this.setTitle( title );

  } // setupTitle --------------------------------------------------------------


  // updateTitle +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * This method adds or removes the changed marker (*) on the title.
   */
  public void updateTitle( boolean changed ) {
    String title;

    title = this.getTitle();
    if ( changed ) {
      if ( !title.endsWith( "*" ) ) {
        this.setTitle( title + "*" );
      } // if
    } // if
    else {
      if ( title.endsWith( "*" ) ) {
        title = title.substring( 0, title.length() - 1 );
        this.setTitle( title );
      } // if
    } // else

  } // updateTitle -------------------------------------------------------------


  /*****************************************************************************
  * Events                                                                     *
  *****************************************************************************/
  // addInven ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void addInven() {
    String              newName;
    InventoryListObject inventoryListObject;
    InventoryPanel      inventoryPanel;
    int                 newTab;

    newName = JOptionPane.showInputDialog( Data.getView(), "New Inventory Panel Name", "items" );
    if ( newName != null && newName.length() > 0 ) {

      newTab = tpContent.getSelectedIndex() + 1;
      inventoryListObject = InventoryObjectDAO.addInventoryListObject( newName );
      inventoryPanel      = new InventoryPanel( newName, inventoryListObject.getInventorysItems() );
      tpContent.insertTab( newName, null, inventoryPanel, null, newTab );
      tpContent.setSelectedIndex( newTab );
      Data.setInventoryDirty();
      checkEnableControls();

    } // if

  } // addInven ----------------------------------------------------------------


  // delInven ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void delInven() {
    int    tabIndex;
    String listName;

    if ( tpContent.getTabCount() > 0 ) {

      tabIndex = tpContent.getSelectedIndex();
      listName = tpContent.getTitleAt( tabIndex );
      InventoryObjectDAO.delInventoryListObject( listName );
      tpContent.removeTabAt( tabIndex );
      Data.setInventoryDirty();
      checkEnableControls();

    } // if

  } // delInven ----------------------------------------------------------------


  // checkEnableControls +++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void checkEnableControls() {

    if ( tpContent.getTabCount() > 0 ) {
      menuBar.setControlsEnabled( true );
    } // if
    else {
      menuBar.getMenuActions().setControlsEnabled( false );
    } // else

  } // checkEnableControls -----------------------------------------------------


  // renInven ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  // Rename currently displayed inventory panel - name in tab and in memory
  public void renInven() {
    int    index;
    String oldName, newName;
    InventoryListObject inventoryListObject;

    if ( tpContent.getTabCount() > 0 ) {

      index   = tpContent.getSelectedIndex();
      oldName = tpContent.getTitleAt( index );

      newName = JOptionPane.showInputDialog( Data.getView(), "New Inventory Panel Name", oldName );
      if ( newName != null && newName.length() > 0 && !newName.equals( oldName ) ) {

        inventoryListObject = InventoryObjectDAO.getInventoryListObject( oldName );
        if ( inventoryListObject != null ) {
          inventoryListObject.setListName( newName );
        } // if
        tpContent.setTitleAt( tpContent.getSelectedIndex(), newName );
        Data.setInventoryDirty();
        checkEnableControls();

      } // if

    } // if

  } // renInven ----------------------------------------------------------------


  // afterInven ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  // Move current panel to position after previous panel. wraps at end
  public void afterInven() {
    int    tabIndex, newIndex;
    String listName;
    InventoryPanel inventoryPanel;

    if ( tpContent.getTabCount() > 0 ) {

      tabIndex = tpContent.getSelectedIndex();
      listName = tpContent.getTitleAt( tabIndex );

      if ( tabIndex < (tpContent.getTabCount() - 1) ) {
        newIndex = tabIndex + 1;
      } // if
      else { // this is last panel, move it to first
        newIndex = 0;
      } // else

        InventoryObjectDAO.moveInventoryListObjectAfter( listName );
        inventoryPanel = (InventoryPanel) tpContent.getComponentAt( tabIndex );
        tpContent.removeTabAt( tabIndex );
        tpContent.insertTab( listName, null, inventoryPanel, null, newIndex );
        tpContent.setSelectedIndex( newIndex );
        Data.setInventoryDirty();


    } // if

  } // afterInven --------------------------------------------------------------


  // beforeInven +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  // Move current panel to position before previous panel. wraps at beginning
  public void beforeInven() {
    int    tabIndex, newIndex;
    String listName;
    InventoryPanel inventoryPanel;

    if ( tpContent.getTabCount() > 0 ) {

      tabIndex = tpContent.getSelectedIndex();
      listName = tpContent.getTitleAt( tabIndex );

      if ( tabIndex > 0 ) {
        newIndex = tabIndex - 1;
      } // if
      else { // this is the first panel, move it to last
        newIndex = tpContent.getTabCount() - 1;
      } // else

      InventoryObjectDAO.moveInventoryListObjectBefore( listName );
      inventoryPanel = (InventoryPanel) tpContent.getComponentAt( tabIndex );
      tpContent.removeTabAt( tabIndex );
      tpContent.insertTab( listName, null, inventoryPanel, null, newIndex );
      tpContent.setSelectedIndex( newIndex );
      Data.setInventoryDirty();

    } // if

  } // beforeInven -------------------------------------------------------------


  // clearCurInven +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void clearCurInven() {
    int    tabIndex;
    String listName;
    InventoryPanel inventoryPanel;

    if ( tpContent.getTabCount() > 0 ) {

      tabIndex = tpContent.getSelectedIndex();
      listName = tpContent.getTitleAt( tabIndex );
      InventoryObjectDAO.clearInventoryListObject( listName );
      inventoryPanel = (InventoryPanel) tpContent.getComponentAt( tabIndex );
      inventoryPanel.setItemList( new ArrayList<InventoryItemObject>() );
      inventoryPanel.processInventory();
      Data.setInventoryDirty();
      checkEnableControls();

    } // if

  } // clearCurInven -----------------------------------------------------------


  // removeAllInven ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void removeAllInven() {

    InventoryObjectDAO.removeAllInventory();

    tpContent.removeAll();
    _currentPanel = 0;
    Data.setInventoryDirty();
    checkEnableControls();

  } // removeAllInven ----------------------------------------------------------


  // nextPanel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void nextPanel() {

    _currentPanel++;
    if ( _currentPanel >= tpContent.getTabCount() ) {
      _currentPanel = 0;
    } // if
    tpContent.setSelectedIndex( _currentPanel );

  } // nextPanel ---------------------------------------------------------------


  // prevPanel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void prevPanel() {

    _currentPanel--;
    if ( _currentPanel < 0 ) {
      _currentPanel = tpContent.getTabCount() - 1;
    } // if
    tpContent.setSelectedIndex( _currentPanel );

  } // prevPanel ---------------------------------------------------------------


  /*****************************************************************************
  * GUI Methods                                                                *
  *****************************************************************************/
  // initGUI +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void initGUI() {
    this.getContentPane().setBackground( GUI.colorFrameBack );
    this.setDefaultCloseOperation( EXIT_ON_CLOSE );
    this.setLayout(    new BorderLayout() );
    this.setResizable( false );
    this.setBackground( GUI.colorFrameBack ); // set behind menuBar

    imagePath = View.class.getClassLoader().getResource( Data.APP_ICON );
    imageApp = Toolkit.getDefaultToolkit().createImage( imagePath );
    this.setIconImage( imageApp );

    // attributes ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    menuBar = new Menu();
    menuBar.setOpaque( false );

    tpContent = new JTabbedPane();
    tpContent.setBackground( GUI.colorFrameBack );
    tpContent.setEnabled( false );
    tpContent.setFont( GUI.fontSsP14 );
    tpContent.setOpaque( false );
    tpContent.setTabLayoutPolicy( JTabbedPane.SCROLL_TAB_LAYOUT );

    // adds ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    this.setJMenuBar( menuBar );

    this.add( tpContent, BorderLayout.CENTER );

  } // initGUI -----------------------------------------------------------------


  /*****************************************************************************
  * GUI Components                                                             *
  *****************************************************************************/
  URL         imagePath;
  Image       imageApp;
  Menu        menuBar; 
  JTabbedPane tpContent;


  /*****************************************************************************
  * Listeners                                                                  *
  *****************************************************************************/
  @Override
  // processWindowEvent ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  protected void processWindowEvent( WindowEvent event ) {
    boolean proceed = true;

    if ( event.getID() == WindowEvent.WINDOW_CLOSING ) {
      proceed = BuildariaInventoryEditor.exitApp();
    } // if
    if ( proceed ) {
      super.processWindowEvent( event );
    } // if
  } // processWindowEvent ------------------------------------------------------


} // View ======================================================================


/** ============================================================================
Copyright (c) fishBowl softWare Inc. All rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
