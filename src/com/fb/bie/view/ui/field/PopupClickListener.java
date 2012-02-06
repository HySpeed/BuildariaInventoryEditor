package com.fb.bie.view.ui.field;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.fb.bie.Data;
import com.fb.bie.view.ui.field.PopupWiki;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// PopupClickListener **********************************************************
public class PopupClickListener extends MouseAdapter {
  String _wikiTopic = null;


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // setWikiTopic ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public void setWikiTopic( String wikiTopic ) {
    _wikiTopic = wikiTopic;
  } // setWikiTopic ------------------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/
  // showPopup +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void showPopup( MouseEvent event ){
    PopupWiki popupMenu;

    popupMenu = new PopupWiki( _wikiTopic );

    if ( ( _wikiTopic != null )
      && ( !_wikiTopic.equals( Data.NO_ITEM_NAME ) ) ) {
      popupMenu.show( event.getComponent(), event.getX(), event.getY() );
    } // if

  } // showPopup ---------------------------------------------------------------


  /*****************************************************************************
  * Events                                                                     *
  *****************************************************************************/


  /*****************************************************************************
  * Listeners                                                                  *
  *****************************************************************************/


  /*****************************************************************************
  * Class Extensions                                                           *
  *****************************************************************************/
  // mousePressed ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  @Override
  public void mousePressed( MouseEvent event ){
    if ( event.isPopupTrigger() ) { showPopup( event ); } // if
  } // mousePressed ------------------------------------------------------------


  // mouseReleased +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  @Override
  public void mouseReleased( MouseEvent event ){
    if ( event.isPopupTrigger() ) { showPopup( event ); } // if
  } // mouseReleased -----------------------------------------------------------


} // PopupClickListener ========================================================


/** ============================================================================
Some portions Copyright (c) fishBowl softWare Inc. Some rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
