/*******************************************************************************
 * Copyright (c) 2016, 2020 Eurotech and/or its affiliates and others
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
package org.eclipse.kapua.service.device.management.snapshot;

import org.eclipse.kapua.model.KapuaObjectFactory;

/**
 * Device snapshot entity service factory definition.
 *
 * @since 1.0
 */
public interface DeviceSnapshotFactory extends KapuaObjectFactory {

    /**
     * Creates a new {@link DeviceSnapshot}
     *
     * @return
     */
    DeviceSnapshot newDeviceSnapshot();

    /**
     * Creates a new {@link DeviceSnapshots}
     *
     * @return
     */
    DeviceSnapshots newDeviceSnapshots();
}
