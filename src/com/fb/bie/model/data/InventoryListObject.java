package com.fb.bie.model.data;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// InventoryListObject *********************************************************
@XmlRootElement
@XmlType ( propOrder = { "listName", "inventoryItems" } )
public class InventoryListObject {
  private String listName;
  @XmlElementWrapper( name = "Items" )
  @XmlElement( name = "InventoryItem" )
  private ArrayList<InventoryItemObject> inventoryItems;


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  // InventoryListObject *******************************************************
  public InventoryListObject() {
    this.inventoryItems = new ArrayList<InventoryItemObject>();
  } // InventoryListObject -----------------------------------------------------


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // setListName +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setListName( String newListName ) {
    this.listName = newListName;
  } // setListName -------------------------------------------------------------
  // getListName +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  @XmlElement( name = "Name" )
  public String getListName() {
    return listName;
  } // getListName -------------------------------------------------------------


  // getInventorysItems ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public ArrayList<InventoryItemObject> getInventorysItems() { // must be plural
    return inventoryItems;
  } // getInventorysItems ------------------------------------------------------
  // setInventoryItems +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setInventoryItems( ArrayList<InventoryItemObject> newInventoryObjects ) {
    this.inventoryItems = newInventoryObjects;
  } // setInventoryItems -------------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/


  /*****************************************************************************
  * Class Extensions                                                           *
  *****************************************************************************/


} // InventoryListObject =======================================================


/** ============================================================================
Some portions Copyright (c) fishBowl softWare Inc. Some rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
