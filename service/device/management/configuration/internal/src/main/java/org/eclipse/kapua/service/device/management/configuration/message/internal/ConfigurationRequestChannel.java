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
package org.eclipse.kapua.service.device.management.configuration.message.internal;

import org.eclipse.kapua.service.device.management.commons.message.request.KapuaRequestChannelImpl;

/**
 * Device configuration request channel.
 *
 * @since 1.0
 *
 */
public class ConfigurationRequestChannel extends KapuaRequestChannelImpl {

    private String configurationId;
    private String componentId;

    /**
     * Get the device configuration identifier
     *
     * @return
     */
    public String getConfigurationId() {
        return configurationId;
    }

    /**
     * Set the device configuration identifier
     *
     * @param configurationId
     */
    public void setConfigurationId(String configurationId) {
        this.configurationId = configurationId;
    }

    /**
     * Get the device configuration component identifier
     *
     * @return
     */
    public String getComponentId() {
        return componentId;
    }

    /**
     * Set the device configuration component identifier
     *
     * @param componentId
     */
    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }
}
