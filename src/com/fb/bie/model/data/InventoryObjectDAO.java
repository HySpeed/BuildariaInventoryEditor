package com.fb.bie.model.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// InventoryObjectDAO **********************************************************
public class InventoryObjectDAO {
  private static InventoryObject _inventoryObject = null;


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // getInventoryObject ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static InventoryObject getInventoryObject() {
    return _inventoryObject;
  } // getInventoryObject ------------------------------------------------------
  // setInventoryObject ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void setInventoryObject( InventoryObject inventoryObject ) {
    _inventoryObject = inventoryObject;
  } // setInventoryObject ------------------------------------------------------


  // getInventoryListObject ++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static InventoryListObject getInventoryListObject( String listName ) {
    InventoryListObject            result = null;
    ArrayList<InventoryListObject> inventoryLists;
    Iterator<InventoryListObject>  inventoryListObjectsIter;
    InventoryListObject            inventoryListObject;

    if ( _inventoryObject != null ) {

      inventoryLists = _inventoryObject.getInventorysLists();
      inventoryListObjectsIter = inventoryLists.iterator();
      while ( inventoryListObjectsIter.hasNext() ) {

        inventoryListObject = (InventoryListObject) inventoryListObjectsIter.next();
        if ( inventoryListObject != null
          && inventoryListObject.getListName().equalsIgnoreCase( listName ) ) {
          //System.out.println( "~ Get List Name: " + inventoryListObject.getListName() ); // DEV println
          result = inventoryListObject;
        } // if

      } // while

    } // if

    return result;
  } // getInventoryListObject --------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/
  // addInventoryListObject ++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static InventoryListObject addInventoryListObject( String newName ) {
    InventoryListObject            result = null;
    ArrayList<InventoryListObject> inventoryLists;

    if ( _inventoryObject != null ) {
      result = new InventoryListObject();
      result.setListName( newName );
      result.setInventoryItems( new ArrayList<InventoryItemObject>() );
      inventoryLists = _inventoryObject.getInventorysLists();
      inventoryLists.add( result );
    } // if

    return result;
  } // addInventoryListObject --------------------------------------------------


  // clearInventoryListObject ++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void clearInventoryListObject( String listName ) {
    InventoryListObject inventoryListObject;

    inventoryListObject = getInventoryListObject( listName );
    if ( inventoryListObject != null ) {
      inventoryListObject.setInventoryItems( new ArrayList<InventoryItemObject>() );
    } // if

  } // clearInventoryListObject ------------------------------------------------


  // delInventoryListObject ++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void delInventoryListObject( String listName ) {
    ArrayList<InventoryListObject> inventoryLists;
    InventoryListObject            inventoryListObject;

    inventoryLists      = _inventoryObject.getInventorysLists();
    inventoryListObject = getInventoryListObject( listName );
    if ( inventoryLists != null && inventoryListObject != null ) {
      inventoryLists.remove( inventoryListObject );
    } // if

  } // delInventoryListObject --------------------------------------------------


  // moveInventoryListObjectAfter ++++++++++++++++++++++++++++++++++++++++++++++
  public static void moveInventoryListObjectAfter( String listName ) {
    ArrayList<InventoryListObject> inventoryLists;
    InventoryListObject            inventoryListObject;
    int curPosition, newPosition;

    inventoryLists      = _inventoryObject.getInventorysLists();
    inventoryListObject = getInventoryListObject( listName );
    if ( inventoryLists != null && inventoryListObject != null ) {

      curPosition = inventoryLists.indexOf( inventoryListObject );
      if ( curPosition < (inventoryLists.size() - 1) ) {
        newPosition = curPosition + 1;
      } // if
      else { // item is last element, move it to first
        newPosition = 0;
      } // else

      Collections.swap( inventoryLists, curPosition, newPosition );

    } // if

  } // moveInventoryListObjectAfter --------------------------------------------


  // moveInventoryListObjectBefore +++++++++++++++++++++++++++++++++++++++++++++
  public static void moveInventoryListObjectBefore( String listName ) {
    ArrayList<InventoryListObject> inventoryLists;
    InventoryListObject            inventoryListObject;
    int curPosition, newPosition;

    inventoryLists      = _inventoryObject.getInventorysLists();
    inventoryListObject = getInventoryListObject( listName );
    if ( inventoryLists != null && inventoryListObject != null ) {

      curPosition = inventoryLists.indexOf( inventoryListObject );
      if ( curPosition > 0 ) {
        newPosition = curPosition - 1;
      } // if
      else { // item is first element, move it to last
        newPosition = inventoryLists.size() - 1;
      } // else

      Collections.swap( inventoryLists, curPosition, newPosition );

    } // if

  } // moveInventoryListObjectBefore -------------------------------------------


  // removeAllInventory ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void removeAllInventory() {
    _inventoryObject = new InventoryObject();
  } // removeAllInventory ------------------------------------------------------


  // createEmptyInventoryItemObject ++++++++++++++++++++++++++++++++++++++++++++
  public static InventoryItemObject createEmptyInventoryItemObject() {
    return new InventoryItemObject();
  } // createEmptyInventoryItemObject ------------------------------------------


  /*****************************************************************************
  * Class Extensions                                                           *
  *****************************************************************************/


} // InventoryObjectDAO ========================================================


/** ============================================================================
Some portions Copyright (c) fishBowl softWare Inc. Some rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
