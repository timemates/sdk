package org.tomadoro.sdk.exceptions

import io.ktor.utils.io.errors.*

public class ConnectionException(message: String) : IOException(message)