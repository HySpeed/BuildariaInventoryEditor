package com.fb.bie.model.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// InventoryItemObject *********************************************************
@XmlRootElement( name = "InventoryItem" )
@XmlType( propOrder = { "itemName", "itemId" } )
public class InventoryItemObject {
  private String itemName;
  private int    itemId;


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  // InventoryItemObject *******************************************************
  public InventoryItemObject() {
    this.itemName = "";
    this.itemId   = 0;
  } // InventoryItemObject -----------------------------------------------------


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // setItemName +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setItemName( String newItemName ) {
    this.itemName = newItemName;
  } // setItemName -------------------------------------------------------------
  // getItemName +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  @XmlElement( name = "Name" )
  public String getItemName() {
    return itemName;
  } // getItemName -------------------------------------------------------------


  // setItemId +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setItemId( int newItemId ) {
    this.itemId = newItemId;
  } // setItemId ---------------------------------------------------------------
  // getItemId +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  @XmlElement( name = "ID" )
  public int getItemId() {
    return itemId;
  } // getItemId ---------------------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/


  /*****************************************************************************
  * Class Extensions                                                           *
  *****************************************************************************/
  // toString ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  @Override
  public String toString() {
    return itemName + " (" + itemId + ")";
  } // toString ----------------------------------------------------------------


} // InventoryItemObject =======================================================


/** ============================================================================
Some portions Copyright (c) fishBowl softWare Inc. Some rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
