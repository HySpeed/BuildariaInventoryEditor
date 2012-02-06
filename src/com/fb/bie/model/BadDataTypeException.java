package com.fb.bie.model;

/**
 * @program: BuildariaInventoryEditor
 * @author : _Jon
 */
// BadDataTypeException ********************************************************
public class BadDataTypeException extends Exception {
  private static final long serialVersionUID = 2340912696734946277L;


  /*****************************************************************************
  * Constructor                                                                *
  *****************************************************************************/
  // BadDataTypeException ++++++++++++++++++++++++++++++++++++++++++++++++++++++
  /**
   * Creates a new instance of <code>BadDataTypeException</code> without detail message.
   */
  public BadDataTypeException() {
    super();
  } // BadDataTypeException ----------------------------------------------------
  // BadDataTypeException ++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public BadDataTypeException( String message ) {
    super( message );
  } // BadDataTypeException ----------------------------------------------------


} // BadDataTypeException ======================================================


/** ============================================================================
Some portions Copyright (c) fishBowl softWare Inc. Some rights reserved.
This software is distributed WITHOUT ANY WARRANTY; without even the
implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
============================================================================ **/
