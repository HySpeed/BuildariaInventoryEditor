package com.fb.bie.model.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import com.fb.bie.Data;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// ItemObjectDAO ***************************************************************
public class ItemObjectDAO {
  private static final ItemObject      EMPTY_ITEM = new ItemObject( 0, Data.NO_ITEM_NAME, ItemTypes.ALL, 1 );
  private static ArrayList<ItemObject> _itemList;


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // setItemObjects ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void setItemObjects( ArrayList<ItemObject> itemList ) {
    _itemList = itemList;
  } // setItemObjects ----------------------------------------------------------


  // getAllItems +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * EMPTY_ITEM is added to the top of the array.
   */ 
  public static ItemObject[] getAllItems() {
    ItemObject[] result;
    ArrayList<ItemObject> itemList;

    itemList = new ArrayList<ItemObject>( _itemList );

    Collections.sort( itemList );
    itemList.add( 0, getEmptyItem() ); // ensures 'Empty' is at top
    result = new ItemObject[itemList.size()];
    itemList.toArray( result );

    return result;
  } // getAllItems -------------------------------------------------------------


  // getFilteredItems ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * Apply given filters to all items list and return only those that match.
   * EMPTY_ITEM is added to the top of the sorted list.
   */
  public static ItemObject[] getFilteredItems( boolean[] filters ) {
    ItemObject[] result;
    ArrayList<ItemObject> filteredList = new ArrayList<ItemObject>();
    int index;
    ItemObject[] typeItems;

    for ( index = 1; index < filters.length; index++ ) {
      if ( filters[index] ) {
        typeItems = getItemObjectsByType( index, false ); // no "NoItem"
      filteredList = addAll( filteredList, typeItems );
      } // if
    } // for

    Collections.sort( filteredList );
    filteredList.add( 0, getEmptyItem() ); // ensures 'Empty' is at top
    result = new ItemObject[filteredList.size()];
    filteredList.toArray( result );

    return result;
  } // getFilteredItems --------------------------------------------------------


  // getItemObjectByName +++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static ItemObject getItemObjectByName( String itemName ) {
    ItemObject result = null;
    Iterator<ItemObject>   iterItems;
    ItemObject itemObject;

    iterItems = _itemList.iterator();
    while ( iterItems.hasNext() ) {

      itemObject = (ItemObject) iterItems.next();
      if ( itemObject != null ) {
        if ( itemObject.getItemName().equals( itemName ) ) {
          result = itemObject;
        } // if
      } // if

    } // while

    return result;
  } // getItemObjectByName -----------------------------------------------------


  // getItemObjectsByType ++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * If includeEmptyItem is true, EMPTY_ITEM is added to the top of the results.
   */
  public static ItemObject[] getItemObjectsByType( int     type,
                                                   boolean includeEmptyItem ) {
    ItemObject[] result;
    Iterator<ItemObject> iterItems;
    ItemObject   itemObject;
    ArrayList<ItemObject> items = new ArrayList<ItemObject>();

    if ( type == ItemTypes.ALL ) {
      result = getAllItems();
    } // if
    else {
      iterItems = _itemList.iterator();
      while ( iterItems.hasNext() ) {

        itemObject = (ItemObject) iterItems.next();
        if ( itemObject != null ) {
          if ( itemObject.getItemType() == type ) {
            items.add( itemObject );
          } // if
        } // if

      } // while

      if ( includeEmptyItem ) {
        Collections.sort( items );
        items.add( 0, getEmptyItem() );
      } // if
      result = new ItemObject[items.size()];
      items.toArray( result );
    } // if

    return result;
  } // getItemObjectsByType ----------------------------------------------------


  // getMetalSubType +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static String getMetalSubType( String itemName ) {
    String result = "";
    String[] subTypes = { "0", "Iron", "Gold", "Silver", "Copper" };
    int index = 1;

    for ( index = 1; index < subTypes.length; index++ ) {
      if ( itemName.startsWith(subTypes[index]) ) {
        result = "." + Integer.toString( index );
      } // if
    } // for

    return result;
  } // getMetalSubType ---------------------------------------------------------


  // getColorSubType +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static String getColorSubType( String itemName ) {
    String result = "";
    String[] subTypes = { "0", "Phaseblade", "Phasesaber" };
    int index = 1;

    for ( index = 1; index < subTypes.length; index++ ) {
      if ( itemName.startsWith(subTypes[index]) ) {
        result = "." + Integer.toString( index );
      } // if
    } // for

    return result;
  } // getColorSubType ---------------------------------------------------------


  // getEmptyItem ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static ItemObject getEmptyItem() {
    return EMPTY_ITEM;
  } // getEmptyItem ------------------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/
  // addAll ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private static ArrayList<ItemObject> addAll( ArrayList<ItemObject> list,
                                               ItemObject[]          objects ) {
    ArrayList<ItemObject> result = list;
    int index;

    for ( index = 0; index < objects.length; index++ ) {
      result.add( objects[index] );
    } // for

    return result;
  } // addAll ------------------------------------------------------------------


} // ItemObjectDAO =============================================================


/** ============================================================================
Some portions Copyright (c) fishBowl softWare Inc. Some rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
