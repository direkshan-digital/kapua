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
package org.eclipse.kapua.service.device.management.channel.message.internal;

import org.eclipse.kapua.message.internal.KapuaPayloadImpl;
import org.eclipse.kapua.service.device.management.message.request.KapuaRequestPayload;

import java.util.ArrayList;
import java.util.List;

/**
 * Device bundle information request payload.
 *
 * @since 1.0
 */
public class ChannelRequestPayload extends KapuaPayloadImpl implements KapuaRequestPayload {

    private static final String CHANNEL_NAME_PREFIX = "names";
    private static final String CHANNEL_NAME_PREFIX_DOT = CHANNEL_NAME_PREFIX + ".";

    public void setChannelNames(List<String> channelNames) {
        int i = 0;
        for (String channelName : channelNames) {
            if (channelName != null) {
                getMetrics().put(CHANNEL_NAME_PREFIX_DOT + i++, channelName);
            }
        }
    }

    public List<String> getChannelNames() {
        int i = 0;
        List<String> names = new ArrayList<>();
        String name;
        do {
            name = (String) getMetrics().get(CHANNEL_NAME_PREFIX_DOT + i++);
            if (name != null) {
                names.add(name);
            }
        } while (name != null);

        return names;
    }
}
