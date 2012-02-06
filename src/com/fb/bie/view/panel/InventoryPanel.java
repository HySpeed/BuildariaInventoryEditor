package com.fb.bie.view.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.fb.bie.Data;
import com.fb.bie.model.data.InventoryItemObject;
import com.fb.bie.model.data.InventoryObjectDAO;
import com.fb.bie.model.data.ItemObject;
import com.fb.bie.model.data.ItemObjectDAO;
import com.fb.bie.model.data.ItemTypes;
import com.fb.bie.view.GUI;
import com.fb.bie.view.Splash;
import com.fb.bie.view.dialog.ItemChooserDialog;
import com.fb.bie.view.ui.panel.ItemPanel;
import com.fb.bie.view.ui.panel.ItemPanelInterface;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// InventoryPanel **************************************************************
public class InventoryPanel extends JPanel implements ItemPanelInterface {
  private static final long serialVersionUID = 6643621004075310478L;
  private String                         _listName;
  private ArrayList<InventoryItemObject> _inventoryItemList;
  private ItemPanel                      _itemPanel;


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  // InventoryPanel ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public InventoryPanel( String listName,
                         ArrayList<InventoryItemObject> inventoryItemList ) {
    _listName          = listName;
    _inventoryItemList = inventoryItemList;
    initGUI();
  } // InventoryPanel ----------------------------------------------------------


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // getListName +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public String getListName() {
    return _listName;
  } // getListName -------------------------------------------------------------


  // setItemList +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setItemList( ArrayList<InventoryItemObject> inventoryItemList ) {
    _inventoryItemList = inventoryItemList;
  } // setItemList -------------------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/
  // processInventory ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void processInventory() {
    Iterator<InventoryItemObject> inventoryItemListObjectsIter;

    inventoryItemListObjectsIter = _inventoryItemList.iterator();

    setItemPanels( panelInven,  Data.INVEN_SLOTS,  inventoryItemListObjectsIter ); // 40
    setItemPanels( panelCoin,   Data.COIN_SLOTS,   inventoryItemListObjectsIter ); //  4
    setItemPanels( panelArmor,  Data.ARMOR_SLOTS,  inventoryItemListObjectsIter ); //  3
    setItemPanels( panelAccess, Data.ACCESS_SLOTS, inventoryItemListObjectsIter ); //  5

  } // processInventory --------------------------------------------------------


  // setItemPanels +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void setItemPanels( JPanel panel,
                              int    slots,
                              Iterator<InventoryItemObject> inventoryItemListObjectsIter ) {
    InventoryItemObject inventoryItemObject;
    int        position;
    ItemObject itemObject;
    ItemPanel  itemPanel;

    position = 0;
    while ( position < slots ) {

      itemPanel  = (ItemPanel) panel.getComponent( position );
      setItemPanel( itemPanel, null );

      if ( inventoryItemListObjectsIter.hasNext() ) {

        inventoryItemObject = inventoryItemListObjectsIter.next();
        if ( inventoryItemObject != null ) {
          itemObject = ItemObjectDAO.getItemObjectByName( inventoryItemObject.getItemName() );
          setItemPanel( itemPanel, itemObject );
        } // if

      } // if

      position++;
      Splash.updateSplashProgress( 1 );
      
    } // while

  } // setItemPanels -----------------------------------------------------------


  // updateInventory +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * Loop through all positions of the UI (Inven, Coin, Armor, Access) and assign
   * . to memory objets, even if now empty.
   */
  public void updateInventory() {
    int       index;
    InventoryItemObject inventoryItemObject;

    for ( index = 0; index < Data.XML_ITEMS; index++ ) {

      if ( index < _inventoryItemList.size() ) {
        inventoryItemObject = _inventoryItemList.get( index );
      } // if
      else {
        inventoryItemObject = InventoryObjectDAO.createEmptyInventoryItemObject();
      } // else

      if ( index < Data.INVEN_SLOTS ) {
        inventoryItemObject = updateFromPanel( index, panelInven,  inventoryItemObject );
      } // if
      else if ( index < Data.COIN_SLOTS ) {
        inventoryItemObject = updateFromPanel( index, panelCoin,   inventoryItemObject );
      } // if
      else if ( index < Data.ARMOR_SLOTS ) {
        inventoryItemObject = updateFromPanel( index, panelArmor,  inventoryItemObject );
      } // if
      else if ( index < Data.ACCESS_SLOTS ) {
        inventoryItemObject = updateFromPanel( index, panelAccess, inventoryItemObject );
      } // if

      if ( index < _inventoryItemList.size() ) {
        _inventoryItemList.set( index, inventoryItemObject );
      } // if
      else {
        _inventoryItemList.add( index, inventoryItemObject );
      } // else

    } // for

  } // updateInventory ---------------------------------------------------------


  // updateFromPanel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private InventoryItemObject updateFromPanel( int                 index,
                                               JPanel              inventoryPanel,
                                               InventoryItemObject inventoryItemObject ) {
    InventoryItemObject result = inventoryItemObject;
    ItemPanel           itemPanel;
    
    itemPanel = (ItemPanel) inventoryPanel.getComponent( index );
    if ( itemPanel.getItemObject() != null ) {
      result.setItemName( itemPanel.getItemObject().getItemNameSave() );
      result.setItemId(   itemPanel.getItemObject().getItemId() );
    } // if

    return result;
  } // updateFromPanel ---------------------------------------------------------


  // clearCurrent ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void clearCurrent() {
    int       position;
    ItemPanel itemPanel;

    for ( position = 0; position < Data.INVEN_SLOTS; position++ ) {
      itemPanel  = (ItemPanel) panelInven.getComponent( position );
      setItemPanel(  itemPanel, null );
    } // for
    for ( position = 0; position < Data.COIN_SLOTS; position++ ) {
      itemPanel  = (ItemPanel) panelCoin.getComponent( position );
      setItemPanel(  itemPanel, null );
    } // for
    for ( position = 0; position < Data.ARMOR_SLOTS; position++ ) {
      itemPanel  = (ItemPanel) panelArmor.getComponent( position );
      setItemPanel(  itemPanel, null );
    } // for
    for ( position = 0; position < Data.ACCESS_SLOTS; position++ ) {
      itemPanel  = (ItemPanel) panelAccess.getComponent( position );
      setItemPanel(  itemPanel, null );
    } // for

  } // clearCurrent ------------------------------------------------------------


  // setItemPanel ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void setItemPanel( ItemPanel  itemPanel,
                             ItemObject itemObject  ) {

    if ( itemObject != null && !itemObject.equals( ItemObjectDAO.getEmptyItem() ) ) {
      itemPanel.setItem( itemObject );
    } // if
    else {
      itemPanel.setItem( ItemObjectDAO.getEmptyItem() );
    } // if

  } // setItemPanel ------------------------------------------------------------


  /*****************************************************************************
  * Events                                                                     *
  *****************************************************************************/
  // clickItemPanel ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  // If trash slot (#10) is selected, ignore click and display message
  @Override
  public void clickItemPanel( ItemPanel itemPanel,
                              int       itemType  ) {
    Point            location;
    boolean          showControls;

    showControls = ( itemType != ItemTypes.ALL ) ? false : true;

    _itemPanel = itemPanel;

    if ( itemPanel.getBackground() != GUI.colorDisabled ) { // not trash slot

      location   = itemPanel.getLocationOnScreen();
      location.x = location.x + itemPanel.getWidth(); // shift off of the image
      ItemChooserDialog.itemChooserDialog( Data.getView(),
                                           this,
                                           location,
                                           showControls,
                                           itemType,
                                           itemPanel.getItemObject() );

    } // if
    else {

      JOptionPane.showMessageDialog( Data.getView(),
                                     Data.TRASH_SLOT,
                                     Data.TRASH_SLOT_TITLE,
                                     JOptionPane.ERROR_MESSAGE );
    } // else

  } // clickItemPanel ----------------------------------------------------------


  // chooseItemPanel +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  @Override
  public void chooseItemPanel( ItemObject chosenItem ) {

    if ( chosenItem != null ) {
      setItemPanel( _itemPanel, chosenItem );
    } // if

  } // chooseItemPanel ---------------------------------------------------------


  /*****************************************************************************
  * GUI Methods                                                                *
  *****************************************************************************/
  // initGUI +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void initGUI() {
    int position;
    this.setLayout( new BorderLayout() );
    this.setOpaque( false );

    // attributes ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    panelInven = new JPanel( new GridLayout( 4, 10 ) );
    panelInven.setBorder( GUI.setupAreaBorder( "Inventory" ) );
    panelInven.setOpaque( false );

    panelCoinArmorAccess = new JPanel( new GridLayout( 1, 5 ) );
    panelCoinArmorAccess.setOpaque( false );

    panelCoin = new JPanel( new GridLayout( 4, 1 ) );
    panelCoin.setBorder( GUI.setupAreaBorder( "Coins" ) );
    panelCoin.setOpaque( false );

    panelArmor  = new JPanel( new GridLayout( 4, 1 ) );
    panelArmor.setBorder(     GUI.setupAreaBorder( "Armor" ) );
    panelArmor.setOpaque(     false );

    panelAccess = new JPanel( new GridLayout( 5, 1 ) );
    panelAccess.setBorder(    GUI.setupAreaBorder( "Accss" ) );
    panelAccess.setOpaque(    false );

    // adds ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    this.add( panelInven, BorderLayout.CENTER );
     for ( position = 0; position < Data.INVEN_SLOTS; position++ ) {
       
       if ( position == 10 ) { // #10 is Buildaria trash slot
         panelInven.add( new ItemPanel( this, ItemTypes.ALL, GUI.colorDisabled ) );
       } // if
       else {
         panelInven.add( new ItemPanel( this, ItemTypes.ALL, GUI.colorInvenInv ) );
       } // else
       
     } // for

     this.add( panelCoinArmorAccess, BorderLayout.EAST );
     panelCoinArmorAccess.add( panelCoin );
      for ( position = 0; position < Data.COIN_SLOTS; position++ ) {
        panelCoin.add( new ItemPanel( this, ItemTypes.COIN, GUI.colorInvenCoin ) );
      } // for

     panelCoinArmorAccess.add( panelArmor );
       panelArmor.add(  new ItemPanel( this, ItemTypes.ARMOR_HEAD, GUI.colorEquipArmor ) );
       panelArmor.add(  new ItemPanel( this, ItemTypes.ARMOR_BODY, GUI.colorEquipArmor ) );
       panelArmor.add(  new ItemPanel( this, ItemTypes.ARMOR_LEGS, GUI.colorEquipArmor ) );

     panelCoinArmorAccess.add( panelAccess );
     for ( position = 0; position < Data.ACCESS_SLOTS; position++ ) {
       panelAccess.add( new ItemPanel( this, ItemTypes.ACCESSORY,  GUI.colorEquipAcces ) );
     } // for

  } // initGUI -----------------------------------------------------------------


  /*****************************************************************************
  * GUI Components                                                             *
  *****************************************************************************/
  JPanel panelInven;
  JPanel panelCoinArmorAccess;
  JPanel panelCoin;
  JPanel panelArmor;
  JPanel panelAccess;


  /*****************************************************************************
  * Listeners                                                                  *
  *****************************************************************************/


} // InventoryPanel ============================================================


/** ============================================================================
Copyright (c) fishBowl softWare Inc. All rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
