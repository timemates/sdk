package org.tomadoro.sdk.exceptions

/**
 * Marks that request has invalid data.
 * Usually should not throw, but some restrictions can be applied post factum.
 *
 * @param message server message.
 */
public class BadRequestException(message: String) : Exception(message)