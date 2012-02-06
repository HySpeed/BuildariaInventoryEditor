package com.fb.bie.model.data;

import javax.swing.ImageIcon;

import com.fb.bie.Data;
import com.fb.bie.model.Model;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// ItemObject ******************************************************************
public class ItemObject implements Comparable<ItemObject> {
  private int       _itemId;
  private String    _itemName;
  private int       _itemType;
  private int       _maxCount;
  private ImageIcon _itemImage;

  
  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  // ItemObject ****************************************************************
  public ItemObject( int    itemId,
                     String itemName,
                     int    itemType,
                     int    maxCount  ) {
    
    _itemId   = itemId; // TODO convert to overloaded method (negative numbers)
    _itemName = itemName;
    _itemType = itemType;
    _maxCount = maxCount;
    setItemImage();
    
  } // ItemObject --------------------------------------------------------------


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // getItemId +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public int getItemId() {
    return _itemId;
  } // getItemId ---------------------------------------------------------------
  // setItemId +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setItemId( int itemId ) {
    _itemId = itemId;
  } // setItemId ---------------------------------------------------------------


  // getItemName +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public String getItemName() {
    return _itemName;
  } // getItemName -------------------------------------------------------------
  // setItemName +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setItemName( String itemName ) {
    _itemName = itemName;
  } // setItemName -------------------------------------------------------------
  // getItemNameSave +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public String getItemNameSave() {
    String result = _itemName;
    if ( _itemName.equals( Data.NO_ITEM_NAME ) ) {
      result = "";
    } // if
    return result;
  } // getItemNameSave ---------------------------------------------------------


  // getItemType +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public int getItemType() {
    return _itemType;
  } // getItemType -------------------------------------------------------------
  // setItemType +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setItemType( int itemType ) {
    _itemType = itemType;
  } // setItemType -------------------------------------------------------------


  // getItemMaxCount +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public int getItemMaxCount() {
    return _maxCount;
  } // getItemMaxCount ---------------------------------------------------------
  // setItemMaxCount +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setItemMaxCount( int maxCount ) {
    _maxCount = maxCount;
  } // setItemMaxCount ---------------------------------------------------------


  // getItemImage ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * Get Item Icon - Returns Icon created from Item Name and Item Number (ID).
   * Special handling is needed for sub-items:
   * * Pickaxe(1) / Broadsword(4) / Shortsword(6) / Hammer(7) / Axe(10 / Bow(99)
   * * * Iron(1) / Gold(2) / Silver(3) / Copper(4)
   * * Phaseblade (198, 199, 200, 201, 202, 203)
   * * * Blue(1) / Red(2) / Green(3) / Purple(4) / White(5) / Yellow(6)
   */
  public ImageIcon getItemImage() {
    return _itemImage;
  } // getItemImage ------------------------------------------------------------
  // setItemImage ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * Special handling is needed for sub-items:
   * * Pickaxe(1) / Broadsword(4) / Shortsword(6) / Hammer(7) / Axe(10 / Bow(99)
   * * * Iron(1) / Gold(2) / Silver(3) / Copper(4)
   * * Phaseblade / Phasesaber (198, 199, 200, 201, 202, 203)
   * * * Blue(1) / Red(2) / Green(3) / Purple(4) / White(5) / Yellow(6)
   */
  private void setItemImage() {
    String imagePath = "Item_" + _itemId; // default
// TODO this needs to be re-done for new 'netID' in 1.1.2.
    if ( ( _itemId == 1 ) // Pickaxe
      || ( _itemId == 4 ) // Broadsword
      || ( _itemId == 6 ) // Shortsword
      || ( _itemId == 7 ) // Hammer
      || ( _itemId == 10 ) // Axe
      || ( _itemId == 99 ) // Bow
                           ) {
      imagePath = imagePath + ItemObjectDAO.getMetalSubType( _itemName );
    } // if

    if ( ( _itemId == 198 ) // Blue
      || ( _itemId == 199 ) // Red
      || ( _itemId == 200 ) // Green
      || ( _itemId == 201 ) // Purple
      || ( _itemId == 202 ) // White
      || ( _itemId == 203 ) // Yellow
                            ) {
      imagePath = imagePath + ItemObjectDAO.getColorSubType( _itemName );
    } // if

    _itemImage = Model.loadImageIcon( Data.IMAGE_ROOT + "/items/" + imagePath + ".png" );

  } // setItemImage ------------------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/


  /*****************************************************************************
  * Class Extensions                                                           *
  *****************************************************************************/
  // compareTo +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  @Override
  public int compareTo( ItemObject object ) {
    int result = 0;

    if ( object instanceof ItemObject ) {
      ItemObject itemObject = (ItemObject) object;
      result = _itemName.compareTo( itemObject.getItemName() );
    } // if

    return result;
  } // compareTo ---------------------------------------------------------------


  // toString ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  @Override
  public String toString() {
    return _itemName;
  } // toString ----------------------------------------------------------------


} // ItemObject ================================================================


/** ============================================================================
Some portions Copyright (c) fishBowl softWare Inc. Some rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
