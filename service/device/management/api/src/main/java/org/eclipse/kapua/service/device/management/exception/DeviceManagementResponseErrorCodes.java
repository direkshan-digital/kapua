/*******************************************************************************
 * Copyright (c) 2020 Eurotech and/or its affiliates and others
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.service.device.management.exception;

public enum DeviceManagementResponseErrorCodes implements DeviceManagementErrorCodes {

    /**
     * Response parse exception
     */
    RESPONSE_PARSE_EXCEPTION,

    /**
     * Bad request
     */
    RESPONSE_BAD_REQUEST,

    /**
     * Response not found
     */
    RESPONSE_NOT_FOUND,

    /**
     * Response internal error
     */
    RESPONSE_INTERNAL_ERROR
}
