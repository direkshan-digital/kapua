/*******************************************************************************
 * Copyright (c) 2017, 2020 Eurotech and/or its affiliates and others
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
package org.eclipse.kapua.service.device.management.registry.operation.internal;

import org.eclipse.kapua.commons.model.query.AbstractKapuaQuery;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.device.management.registry.operation.DeviceManagementOperationQuery;

/**
 * {@link DeviceManagementOperationQueryImpl} definition.
 *
 * @since 1.0
 */
public class DeviceManagementOperationQueryImpl extends AbstractKapuaQuery implements DeviceManagementOperationQuery {

    private DeviceManagementOperationQueryImpl() {
        super();
    }

    public DeviceManagementOperationQueryImpl(KapuaId scopeId) {
        this();

        setScopeId(scopeId);
    }

}
