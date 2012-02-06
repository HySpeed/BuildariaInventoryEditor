package com.fb.bie.model.data;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// InventoryObject *************************************************************
@XmlRootElement( name = "ArrayOfInventory" )
public class InventoryObject {
  @XmlElement( name = "Inventory")
  private ArrayList<InventoryListObject> inventoryLists;


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  // InventoryObject ***********************************************************
  public InventoryObject() {
    this.inventoryLists = new ArrayList<InventoryListObject>();
  } // InventoryObject ---------------------------------------------------------


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // getInventorysLists ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public ArrayList<InventoryListObject> getInventorysLists() {
    return inventoryLists;
  } // getInventorysLists ------------------------------------------------------
  // setInventoryLists +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setInventoryLists( ArrayList<InventoryListObject> newInventoryLists ) {
    this.inventoryLists = newInventoryLists;
  } // setInventoryLists -------------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/


  /*****************************************************************************
  * Class Extensions                                                           *
  *****************************************************************************/


} // InventoryObject ===========================================================


/** ============================================================================
Some portions Copyright (c) fishBowl softWare Inc. Some rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
