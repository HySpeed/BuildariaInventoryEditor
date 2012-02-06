package com.fb.bie.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.geom.Rectangle2D;

import com.fb.bie.Data;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// Splash **********************************************************************
public class Splash {
  private static SplashScreen       _splashScreen;       // Instantiated by JVM we use it to get graphics
  private static Graphics2D         _splashGraphics;     // Graphics context for overlay of the splash image
  private static Rectangle2D.Double _splashProgressArea; // Area where we draw the progress bar
  private static long               _lastUpdateTime = 0;


  /*****************************************************************************
  * Accessors                                                                  *
  *****************************************************************************/
  // updateSplashProgress ++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void updateSplashProgress( int amount ) {
    int percent;

    percent = Data.incSplashProgress( amount );
    if ( isTimeToUpdate() ) {
      setSplashProgress( percent );
    } // if

  } // updateSplashProgress ----------------------------------------------------


  // isTimeToUpdate ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private static boolean isTimeToUpdate() {
    boolean result = false;
    final   long newTime;

    if ( _lastUpdateTime == 0 ) {
      _lastUpdateTime = System.currentTimeMillis();
      result = true;
    } // if

    newTime = System.currentTimeMillis();

    if ( newTime - _lastUpdateTime > 33 ) {
      _lastUpdateTime = newTime;
      result = true;
    } // if

    return result;
  } // isTimeToUpdate ----------------------------------------------------------


  // setSplashProgress +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void setSplashProgress( int percent ) {
    int x, y, width, heigt, doneWidth;

    if ( _splashScreen != null && _splashScreen.isVisible() ) {

      // Calculate the width corresponding to the correct percentage
      x     = (int) _splashProgressArea.getMinX();
      y     = (int) _splashProgressArea.getMinY();
      width = (int) _splashProgressArea.getWidth();
      heigt = (int) _splashProgressArea.getHeight();

      // CUSTOM VALUES
      doneWidth = Math.round( percent * width / 660.f );
      doneWidth = Math.max( 0, Math.min( doneWidth, width-1 ) );  // limit 0-width

      _splashGraphics.setPaint( Color.GREEN );
      _splashGraphics.fillRect( x, y, doneWidth, heigt );

      _splashScreen.update(); // force update
    } // if

  } // setSplashProgress -------------------------------------------------------


  /*****************************************************************************
  * Data Processing                                                            *
  *****************************************************************************/
  // initSplashScreen ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void initSplashScreen() {
    Dimension dim;
    int       height, width;

    _splashScreen = SplashScreen.getSplashScreen();
    if ( _splashScreen != null ) {

      dim    = _splashScreen.getSize();
      height = dim.height;
      width  = dim.width;

      // stake out some area for our status information
      // CUSTOM VALUES
      _splashProgressArea = new Rectangle2D.Double( (width  * .25),
                                                    (height * .9),
                                                    (width  * .50),
                                                     12 );

      // create the Graphics environment for drawing status info
      _splashGraphics = _splashScreen.createGraphics();

      // draw an outline
      _splashGraphics.setPaint(Color.WHITE);
      _splashGraphics.draw( _splashProgressArea );

      // initialize the status info
      setSplashProgress( 0 );

    } // if

  } // initSplashScreen --------------------------------------------------------


  // pauseProgress +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void pauseSplashProgress( int milliSec ) {

    try {
      Thread.sleep( milliSec );
    } // try
    catch ( InterruptedException ignored ) {} // catch
    
  } // pauseProgress -----------------------------------------------------------


  // closeSplashScreen +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void closeSplashScreen() {

    if ( _splashScreen != null && _splashScreen.isVisible() ) {
      _splashScreen.close();
    } // if

  } // closeSplashScreen -------------------------------------------------------


} // Splash ====================================================================


/** ============================================================================
Some portions Copyright (c) fishBowl softWare Inc. Some rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
