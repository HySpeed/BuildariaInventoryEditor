package com.fb.bie.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.fb.bie.Data;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// GUI *************************************************************************
public class GUI {

  // Colors ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static final Color  colorFrameBack        = new Color( 150, 200, 250 );
  public static final Color  colorEditBack         = new Color( 225, 200, 125 );
  public static final Color  colorVersionBack      = new Color( 160, 180, 200 );

  public static final Color  colorInvenInv         = new Color( 100, 200, 200 );
  public static final Color  colorInvenCoin        = new Color( 100, 200, 100 );
  public static final Color  colorInvenAmmo        = new Color(   0, 150, 100 );
  public static final Color  colorItemCell         = new Color( 250, 230, 175 );
  public static final Color  colorItemSelCell      = new Color( 250, 250,   0 );
  public static final Color  colorItemFocusCell    = new Color( 250, 250, 200 );
  public static final Color  colorChooserBack      = Color.GRAY;
  public static final Color  colorChooserFront     = Color.LIGHT_GRAY;
  public static final Color  colorPopupBack        = new Color( 190, 190, 190 );
  public static final Color  colorEquipSocial      = new Color( 200, 150, 100 );
  public static final Color  colorEquipArmor       = new Color( 220, 120,  50 );
  public static final Color  colorEquipAcces       = new Color( 150, 100,  50 );

  public static final Color  colorEditGood         = new Color(   0, 150,   0 );
  public static final Color  colorEditBad          = Color.RED;
  public static final Color  colorEditOver         = Color.WHITE;
  public static final Color  colorDisabled         = Color.GRAY;


  public static final String colorTipItemName      = "white";
  public static final String colorTipItemType      = "yellow";
  public static final String colorTipEquipName     = "maroon";
  public static final String colorTipEquipType     = "yellow";

  // Borders ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static final Border borderEtched      = BorderFactory.createEtchedBorder(   Color.WHITE, new Color( 148, 145, 140 ) );
  public static final Border borderEmpty2px    = BorderFactory.createEmptyBorder(    2, 2, 2, 2 );
  public static final Border borderEmptyLR2px  = BorderFactory.createEmptyBorder(    0, 2, 0, 2 );
  public static final Border borderEmptyLR5px  = BorderFactory.createEmptyBorder(    0, 5, 0, 5 );
  public static final Border borderEmptyLRT5px = BorderFactory.createEmptyBorder(    5, 5, 0, 5 );
  public static final Border borderLineBL1px   = BorderFactory.createLineBorder(     Color.BLACK, 1 );
  public static final Border borderLineLG1px   = BorderFactory.createLineBorder(     Color.LIGHT_GRAY, 1 );
  public static final Border borderLineGR1px   = BorderFactory.createLineBorder(     Color.GRAY, 1 );
  public static final Border borderList        = BorderFactory.createCompoundBorder( borderLineLG1px, borderEmptyLR5px );
  public static final Border borderChooserCtls = BorderFactory.createCompoundBorder( borderLineGR1px, borderEmptyLR5px );
  public static final Border borderEditLow     = BorderFactory.createBevelBorder(    BevelBorder.LOWERED, Color.LIGHT_GRAY, Color.GRAY );
  public static final Border borderEditField   = BorderFactory.createCompoundBorder( borderEditLow, borderEmptyLR5px );
  public static final Border borderIconPanel   = BorderFactory.createCompoundBorder( borderEditLow, borderEmptyLR5px );
  public static final Border borderItemPanel   = BorderFactory.createRaisedBevelBorder();

  // Fonts ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static final Font   fontSsP10  = new Font( "SansSerif", Font.PLAIN,  10 );
  public static final Font   fontSsP12  = new Font( "SansSerif", Font.PLAIN,  12 );
  public static final Font   fontSsB12  = new Font( "SansSerif", Font.BOLD,   12 );
  public static final Font   fontSsP14  = new Font( "SansSerif", Font.PLAIN,  14 );
  public static final Font   fontSsB14  = new Font( "SansSerif", Font.BOLD,   14 );
  public static final Font   fontSsI14  = new Font( "SansSerif", Font.ITALIC, 14 );
  public static final Font   fontSsBI14 = new Font( "SansSerif", Font.BOLD | Font.ITALIC, 14 );
  public static final Font   fontSsP16  = new Font( "SansSerif", Font.PLAIN,  16 );

  // ImageIcons ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  public static final Dimension DIM_ICON  = new Dimension( 50, 50 );

  public static final String ACTIONS_OPEN   = Data.IMAGE_ROOT + "/ui/MenuFileOpen";
  public static final String ACTIONS_SAVE   = Data.IMAGE_ROOT + "/ui/MenuFileSave";
  public static final String ACTIONS_SAVEAS = Data.IMAGE_ROOT + "/ui/MenuFileSaveAs";

  public static final String ACTIONS_ADD_INVEN        = Data.IMAGE_ROOT + "/ui/MenuActionsAddInven";
  public static final String ACTIONS_DEL_INVEN        = Data.IMAGE_ROOT + "/ui/MenuActionsDelInven";
  public static final String ACTIONS_REN_INVEN        = Data.IMAGE_ROOT + "/ui/MenuActionsRenInven";
  public static final String ACTIONS_BEFORE_INVEN     = Data.IMAGE_ROOT + "/ui/MenuActionsBeforeInven";
  public static final String ACTIONS_AFTER_INVEN      = Data.IMAGE_ROOT + "/ui/MenuActionsAfterInven";
  public static final String ACTIONS_CLEAR_CUR_INVEN  = Data.IMAGE_ROOT + "/ui/MenuActionsClearCurInven";
  public static final String ACTIONS_REMOVE_ALL_INVEN = Data.IMAGE_ROOT + "/ui/MenuActionsRemoveAllInven";


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/
  // setupAreaBorder +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static TitledBorder setupAreaBorder( String title ) {
    return new TitledBorder( GUI.borderEtched,
                             " " + title + " ",
                             TitledBorder.DEFAULT_JUSTIFICATION,
                             TitledBorder.DEFAULT_POSITION,
                             GUI.fontSsP14 );
  } // setupAreaBorder ---------------------------------------------------------


} // GUI =======================================================================


/** ============================================================================
Some portions Copyright (c) fishBowl softWare Inc. Some rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
