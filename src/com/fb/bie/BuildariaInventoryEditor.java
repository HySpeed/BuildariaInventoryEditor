package com.fb.bie;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JOptionPane;

import com.fb.bie.model.Model;
import com.fb.bie.view.Splash;
import com.fb.bie.view.View;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// BuildariaInventoryEditor ****************************************************
public class BuildariaInventoryEditor {

  
  /*****************************************************************************
  * main                                                                       *
  *****************************************************************************/
  // main ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void main( String[] args ) {
    String message;

    try {
      new BuildariaInventoryEditor();
    } // try
    catch ( NoClassDefFoundError error ) {
      message = Data.LIBRARY_ERROR + error.getMessage();
      JOptionPane.showMessageDialog( null, message, "Fatal error", JOptionPane.ERROR_MESSAGE );
      throwError();
    } // catch

  } // main --------------------------------------------------------------------


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  // BuildariaInventoryEditor ++++++++++++++++++++++++++++++++++++++++++++++++++
  public BuildariaInventoryEditor() {

    Splash.initSplashScreen();

    Data.setModel( new Model() );
    Data.setView(  new View()  );

    Data.getView().getMenu().getMenuFile().openFile( new File( Data.INVENTORY_FILE ) );

    java.awt.EventQueue.invokeLater(
      new Runnable() { @Override
        public void run() {
          Data.getView().validate();
          Data.getView().pack();
          positionWindow();
          Data.getView().setVisible( true );
        } // run
      } // runnable
    ); // invokeLater

    Splash.setSplashProgress( 1000 );
    Splash.pauseSplashProgress( 500 );
    Splash.closeSplashScreen();

  } // BuildariaInventoryEditor ------------------------------------------------


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/

  
  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/

  
  /*****************************************************************************
  * Application Exit                                                           *
  *****************************************************************************/
  // throwError ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void throwError() {
    System.exit( 1 );
  } // throwError --------------------------------------------------------------


  // exitApp +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * Query and possibly exit app.
   */
  public static boolean exitApp() {
    boolean result = true;
    int     proceed = JOptionPane.YES_OPTION;
    
//    System.exit( 0 ); // DEV exit quickly

    if ( Data.getInventoryChanged() ) {
      proceed = JOptionPane.showConfirmDialog( null, 
                                               Data.CHANGED_MSG, 
                                               Data.CONFIRM_TITLE, 
                                               JOptionPane.YES_NO_OPTION );
    } // if
    
    if ( proceed == JOptionPane.YES_OPTION ) {
      System.exit( 0 );
    } // if
    else {
      result = false;
    } // else
    
    return result;
  } // exitApp -----------------------------------------------------------------

  
  /*****************************************************************************
  * GUI Methods                                                                *
  *****************************************************************************/
  // positionWindow ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  // Vert: 20% below top
  // Hori: center of screen
  private void positionWindow() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize  = Data.getView().getSize();
    int       x, y;

    if ( frameSize.height > screenSize.height ) {
         frameSize.height = screenSize.height;
    } // if

    if ( frameSize.width > screenSize.width ) {
         frameSize.width = screenSize.width;
    } // if

    x = (screenSize.width  - frameSize.width  ) / 2;
    y = (int) (screenSize.height * .2);

    Data.getView().setLocation( x, y );

  } // positionWindow ----------------------------------------------------------


} // BuildariaInventoryEditor ==================================================


/** ============================================================================
Copyright (c) fishBowl softWare Inc. All rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
