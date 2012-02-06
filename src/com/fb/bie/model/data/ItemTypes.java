package com.fb.bie.model.data;

import com.fb.bie.Data;
import com.fb.bie.model.BadDataTypeException;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// ItemTypes *******************************************************************
public class ItemTypes {
  public  static final int ALL               =  0;
  public  static final int ACCESSORY         =  1;
  public  static final int AMMUNITION        =  2;
  public  static final int ARMOR_BODY        =  3;
  public  static final int ARMOR_HEAD        =  4;
  public  static final int ARMOR_LEGS        =  5;
  public  static final int BUILDING          =  6;
  public  static final int COIN              =  7;
  public  static final int CONSUMABLE        =  8;
  public  static final int FURNITURE         =  9;
  public  static final int LIGHTING          = 10;
  public  static final int MISCELLANEOUS     = 11;
  public  static final int ORE               = 12;
  public  static final int POTION            = 13;
  public  static final int STATUE            = 14;
  public  static final int TOOL_AXE          = 15;
  public  static final int TOOL_CHAINSAW     = 16;
  public  static final int TOOL_DRILL        = 17;
  public  static final int TOOL_HAMAXE       = 18;
  public  static final int TOOL_HAMMER       = 19;
  public  static final int TOOL_OTHER        = 20;
  public  static final int TOOL_PICK         = 21;
  public  static final int WEAPON_BOOMERANG  = 22;
  public  static final int WEAPON_BOW        = 23;
  public  static final int WEAPON_EXPLOSIVE  = 24;
  public  static final int WEAPON_FLAIL      = 25;
  public  static final int WEAPON_GUN        = 26;
  public  static final int WEAPON_MAGICAL    = 27;
  public  static final int WEAPON_MELEE      = 28;
  public  static final int WEAPON_SPEAR      = 29;
  public  static final int WEAPON_THROWN     = 30;

  public  static final int NUM_TYPES = 31;

  /**
   * Used when reading image info from csv file.
   */
  public static final String[] ITEM_TYPES = { "All",
                                              "Accessory",
                                              "Ammunition",
                                              "Armor_Body",
                                              "Armor_Head",
                                              "Armor_Legs",
                                              "Building",
                                              "Coin",
                                              "Consumable",
                                              "Furniture",
                                              "Lighting",
                                              "Miscellaneous",
                                              "Ore",
                                              "Potion",
                                              "Statue",
                                              "Tool_Axe",
                                              "Tool_Chainsaw",
                                              "Tool_Drill",
                                              "Tool_HamAxe",
                                              "Tool_Hammer",
                                              "Tool_Other",
                                              "Tool_Pick",
                                              "Weapon_Boomerang",
                                              "Weapon_Bow",
                                              "Weapon_Explosive",
                                              "Weapon_Flail",
                                              "Weapon_Gun",
                                              "Weapon_Magical",
                                              "Weapon_Melee",
                                              "Weapon_Spear",
                                              "Weapon_Thrown"
  };


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // getItemType +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * Returns the type number given the string name.
   * Comparison is not case sensitive.
   */
  public static int getItemType( String itemType ) throws BadDataTypeException {
    int result = Data.NO_SELECTION;
    int index;

    for ( index = 1; index < ITEM_TYPES.length; index++ ) {
      if ( itemType.equalsIgnoreCase( ITEM_TYPES[index] ) ) {
        result = index;
        break;
      } // if
    } // for

    if ( result == Data.NO_SELECTION ) {
      throw new BadDataTypeException( "! Item Type '" + itemType + "' is not valid" );
    } // if

    return result;
  } // getItemType -------------------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/


} // ItemTypes =================================================================


/** ============================================================================
Some portions Copyright (c) fishBowl softWare Inc. Some rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
