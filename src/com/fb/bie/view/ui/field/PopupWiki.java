package com.fb.bie.view.ui.field;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import com.fb.bie.Data;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// PopupWiki *******************************************************************
public class PopupWiki extends JPopupMenu {
  private static final long serialVersionUID = -6946728405317626250L;
  private              String _wikiTopic;


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  public PopupWiki( String wikiTopic ){
    _wikiTopic = wikiTopic;
    initGUI();
  } // PopupWiki ---------------------------------------------------------------


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  String getWikiTopic() {
    return _wikiTopic;
  } // getWikiTopic ------------------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/
  // underscoreName ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private String  underscoreName( String name ) {
    return name.replaceAll( " ", "_" );
  } // underscoreName ----------------------------------------------------------


  /*****************************************************************************
  * Events                                                                     *
  *****************************************************************************/
  // eventMenuPopup ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void eventMenuPopup(  ) {

  try {

      URI uri = new URI( Data.WIKI_URL + underscoreName( getWikiTopic() ) );
      Desktop desktop = null;
      if ( Desktop.isDesktopSupported() ) {
        desktop = Desktop.getDesktop();
      } // if

      if (desktop != null) {
        desktop.browse(uri);
      } // if

    } // try
    catch ( IOException error ) {
      JOptionPane.showMessageDialog( Data.getView(),
                                     "^Wiki Error: " + error.getMessage(),
                                     "Wiki Error: "  + Data.APP_TITLE,
                                     JOptionPane.ERROR_MESSAGE );
    } // catch
    catch ( URISyntaxException error ) {
      JOptionPane.showMessageDialog( Data.getView(),
                                     "@Wiki Error: " + error.getMessage(),
                                     "Wiki Error: "  + Data.APP_TITLE,
                                     JOptionPane.ERROR_MESSAGE );
    } // catch

  } // eventMenuPopup ----------------------------------------------------------


  /*****************************************************************************
  * GUI Methods                                                                *
  *****************************************************************************/
  // initGUI +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private void initGUI() {
    // attributes ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    popupMenu = new JMenuItem( "Show Wiki for: " + _wikiTopic );
    popupMenu.addActionListener( listenerPopupMenu );

    // adds ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    this.add( popupMenu );

  } // initGUI -----------------------------------------------------------------


  /*****************************************************************************
  * GUI Components                                                             *
  *****************************************************************************/
  JMenuItem popupMenu;


  /*****************************************************************************
  * Listeners                                                                  *
  *****************************************************************************/
  ActionListener listenerPopupMenu = new ActionListener()  { @Override
    public void actionPerformed( ActionEvent e ) { eventMenuPopup(); }};


  /*****************************************************************************
  * Class Extensions                                                           *
  *****************************************************************************/


} // PopupWiki =================================================================


/** ============================================================================
Some portions Copyright (c) fishBowl softWare Inc. Some rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
