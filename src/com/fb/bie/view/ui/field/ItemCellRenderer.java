package com.fb.bie.view.ui.field;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import com.fb.bie.model.data.ItemObject;
import com.fb.bie.model.data.ItemTypes;
import com.fb.bie.view.GUI;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// ItemCellRenderer ************************************************************
public class ItemCellRenderer extends DefaultListCellRenderer {
  private static final long serialVersionUID = 1110362056371799295L;
  private ItemObject _currentObject;

  
  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // setCurrentObject ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * Set the current object so it will be highlighted in the list.
   */
  public void setCurrentObject( ItemObject currentObject ) {
    _currentObject = currentObject;
  } // setCurrentObject --------------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/

  
  /*****************************************************************************
  * Events                                                                     *
  *****************************************************************************/

  
  /*****************************************************************************
  * Class Extensions                                                           *
  *****************************************************************************/
  // getListCellRendererComponent ++++++++++++++++++++++++++++++++++++++++++++++
  @Override
  public Component getListCellRendererComponent( JList   list,
                                                 Object  value,
                                                 int     index,
                                                 boolean selected,
                                                 boolean hasFocus ) {
    JLabel      result;
    ItemObject  itemObject;
    ImageIcon   icon;
    String      toolTip;
    int         width, height;
    int         labelWidth, iconGap;
    FontMetrics fontMetrics;

    result = (JLabel) super.getListCellRendererComponent( list, value, index,
                                                          selected, hasFocus );

    if ( value instanceof ItemObject ) {
      itemObject = (ItemObject) value;
      icon       = itemObject.getItemImage();
      result.setBorder(       GUI.borderList   );

      toolTip = "<html><font color=\"" + GUI.colorTipItemName + "\"><b>" + 
                 itemObject.getItemName() + "</b></font><br/>" +
                "<font color=\"" + GUI.colorTipItemType + "\">"  + 
                 ItemTypes.ITEM_TYPES[itemObject.getItemType()] + "</font></html>";
      result.setToolTipText( toolTip );
      
        result.setBackground( GUI.colorItemCell ); 
      if ( itemObject.equals(    _currentObject  ) ) { 
        result.setBackground( GUI.colorItemSelCell ); 
      } // if
      
      if ( selected ) { 
        result.setBackground( GUI.colorItemFocusCell ); 
      } // if

      // this standardizes the alignment of the label in the list
      result.setFont( GUI.fontSsB14 );
      result.setIcon( icon );
      iconGap = 5 + (GUI.DIM_ICON.width - icon.getIconWidth());
      result.setIconTextGap( iconGap );

      // this increases the width of the label to account for the icon and spacing
      fontMetrics = result.getFontMetrics(  result.getFont() );
      labelWidth = fontMetrics.stringWidth( result.getText() );
      width  = (GUI.DIM_ICON.width * 2) + labelWidth;
      height =  GUI.DIM_ICON.height;
      result.setPreferredSize( new Dimension( width, height ) );
      result.setMinimumSize(   new Dimension( width, height ) );
      result.setMaximumSize(   new Dimension( width, height ) );

    } // if

    return result;
  } // getListCellRendererComponent --------------------------------------------


} // ItemCellRenderer ==========================================================


/** ============================================================================
Copyright (c) fishBowl softWare Inc. All rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
