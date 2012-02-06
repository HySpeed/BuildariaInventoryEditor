package com.fb.bie.view.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fb.bie.Data;
import com.fb.bie.model.data.ItemObject;
import com.fb.bie.model.data.ItemObjectDAO;
import com.fb.bie.model.data.ItemTypes;
import com.fb.bie.view.GUI;
import com.fb.bie.view.ui.field.PopupClickListener;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// ItemPanel *******************************************************************
public class ItemPanel extends JPanel {
  private static final long  serialVersionUID = -4070946914888660728L;
  private ItemPanelInterface _parent     = null;
  private int                _itemType   = ItemTypes.ALL;
  private ItemObject         _itemObject = null;
  private String             _itemName   = "";
  private Color              _color      = null;

  
  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  // ItemPanel *****************************************************************
  public ItemPanel( ItemPanelInterface parent,
                    int                itemType,
                    Color              color     ) {
    super();
    _parent   = parent;
    _itemType = itemType;
    _color    = color;
    initGUI();
  } // ItemPanel ---------------------------------------------------------------


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // setItem +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setItem( ItemObject itemObject  ) {
    ImageIcon itemIcon;
    String    itemName, toolTip;

    _itemObject = itemObject;
    itemIcon    = itemObject.getItemImage();
    itemName    = itemObject.getItemName();

    if ( itemName == null || itemName.equals( Data.NO_ITEM_NAME ) ) { 
      itemName  = ""; 
    } // if
    
    _itemName = itemName;
    imageIcon.setIcon( itemIcon );
    
    if ( !_itemObject.equals( ItemObjectDAO.getEmptyItem() ) ) {
      
      labelName.setText( _itemName );
      
      toolTip = "<html><font color=\"" + GUI.colorTipItemName + "\"><b>" + 
                 itemName + "</b></font><br/>" +
                "<font color=\"" + GUI.colorTipItemType + "\">"  + 
                 ItemTypes.ITEM_TYPES[_itemObject.getItemType()] + "</font></html>";
      labelName.setToolTipText( toolTip   );
      imageIcon.setToolTipText( toolTip   );
      
      popupItem.setWikiTopic(   _itemName );
      
    } // if
    else { // no item
      
      labelName.setText(        " " );
      labelName.setToolTipText( Data.NO_ITEM_NAME );
      imageIcon.setToolTipText( Data.NO_ITEM_NAME );
      popupItem.setWikiTopic(   null );
      
    } // else


  } // setItem -----------------------------------------------------------------

  
  // getItemObject +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public ItemObject getItemObject() {
    return _itemObject;
  } // getItemObject -----------------------------------------------------------

  
  // getItemName +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public String getItemName() {
    return _itemName;
  } // getItemName -------------------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/

  
  /*****************************************************************************
  * Events                                                                     *
  *****************************************************************************/
  // clickItemPanel ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void clickItemPanel() {
    _parent.clickItemPanel( (ItemPanel) imageIcon.getParent().getParent(), _itemType );
  } // clickItemPanel ----------------------------------------------------------

  
  /*****************************************************************************
  * GUI Methods                                                                *
  *****************************************************************************/
  // initGUI +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void initGUI() {
    this.setLayout( new BorderLayout() );
    this.setBackground( _color );
    this.setBorder(     GUI.borderItemPanel );

    // attributes ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    FontMetrics fontMetrics;
    int labelHeight, width, height;

    panelItem = new JPanel( new BorderLayout() );
    panelItem.setOpaque( false );
    
    labelName = new JLabel( "" ) ;
    labelName.setFont( GUI.fontSsP14 );
    labelName.setOpaque( true );
    labelName.setBorder( GUI.borderEmptyLR2px );
    if ( _color != GUI.colorDisabled ) {
      labelName.setBackground( _color.brighter().brighter() );
    } // if
    else {
      labelName.setBackground( _color.darker().darker() );
    } // else

    fontMetrics = labelName.getFontMetrics( labelName.getFont() );
    labelHeight = fontMetrics.getHeight();
    width  = GUI.DIM_ICON.width;
    height = labelHeight;
    labelName.setPreferredSize( new Dimension( width, height ) );
    labelName.setMinimumSize(   new Dimension( width, height ) );
    labelName.setMaximumSize(   new Dimension( width, height ) );
    labelName.setToolTipText(   Data.NO_ITEM_NAME );

    imageIcon = new JButton(    ItemObjectDAO.getEmptyItem().getItemImage() );
    imageIcon.setBorder(        GUI.borderIconPanel );
    imageIcon.setContentAreaFilled( false );
    imageIcon.setFocusPainted(  false );
    imageIcon.setPreferredSize( GUI.DIM_ICON  );
    imageIcon.setMinimumSize(   GUI.DIM_ICON  );
    imageIcon.setMaximumSize(   GUI.DIM_ICON  );
    imageIcon.setToolTipText(   Data.NO_ITEM_NAME );
    imageIcon.addActionListener( listenerIcon );
    popupItem = new PopupClickListener();
    imageIcon.addMouseListener( popupItem ); 

    // adds ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    this.add( panelItem,        BorderLayout.CENTER );
      panelItem.add( labelName, BorderLayout.NORTH  );
      panelItem.add( imageIcon, BorderLayout.CENTER );

  } // initGUI -----------------------------------------------------------------

  
  /*****************************************************************************
  * GUI Components                                                             *
  *****************************************************************************/
  JPanel  panelItem;
  JLabel  labelName;
  JButton imageIcon;
  PopupClickListener popupItem;


  /*****************************************************************************
  * Listeners                                                                  *
  *****************************************************************************/
  ActionListener listenerIcon = new ActionListener() { @Override
    public void actionPerformed( ActionEvent event ) { clickItemPanel();        }};


  /*****************************************************************************
  * Class Extensions                                                           *
  *****************************************************************************/


} // ItemPanel =================================================================


/** ============================================================================
Copyright (c) fishBowl softWare Inc. All rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
