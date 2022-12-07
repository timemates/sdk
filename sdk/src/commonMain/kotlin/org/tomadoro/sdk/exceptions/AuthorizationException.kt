package org.tomadoro.sdk.exceptions

import io.ktor.utils.io.errors.*

public class AuthorizationException : IOException("Authorization failed")