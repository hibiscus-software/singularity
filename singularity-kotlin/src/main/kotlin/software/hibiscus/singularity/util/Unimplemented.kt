/*
 * Copyright (C), 2024 Hibiscus Software and contributors. Some rights
 * reserved. This work is licensed under the terms of the MIT license
 * which can be found in the root directory of this project.
 */

package software.hibiscus.singularity.util

/**
 * Specifies that a field or function unimplemented.
 *
 * @param reason The reason that a field or function is unimplemented.
 */
annotation class Unimplemented(val reason: String)
