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
 *      Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.service.device.management.command.internal;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.util.ArgumentValidator;
import org.eclipse.kapua.locator.KapuaProvider;
import org.eclipse.kapua.model.domain.Actions;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.device.management.DeviceManagementDomains;
import org.eclipse.kapua.service.device.management.command.DeviceCommandInput;
import org.eclipse.kapua.service.device.management.command.DeviceCommandManagementService;
import org.eclipse.kapua.service.device.management.command.DeviceCommandOutput;
import org.eclipse.kapua.service.device.management.command.internal.exception.CommandExecuteManagementException;
import org.eclipse.kapua.service.device.management.command.message.internal.CommandRequestChannel;
import org.eclipse.kapua.service.device.management.command.message.internal.CommandRequestMessage;
import org.eclipse.kapua.service.device.management.command.message.internal.CommandRequestPayload;
import org.eclipse.kapua.service.device.management.command.message.internal.CommandResponseMessage;
import org.eclipse.kapua.service.device.management.command.message.internal.CommandResponsePayload;
import org.eclipse.kapua.service.device.management.commons.AbstractDeviceManagementServiceImpl;
import org.eclipse.kapua.service.device.management.commons.call.DeviceCallExecutor;
import org.eclipse.kapua.service.device.management.message.KapuaMethod;
import org.eclipse.kapua.service.device.management.message.response.KapuaResponsePayload;

import java.util.Date;

/**
 * {@link DeviceCommandManagementService} implementation.
 *
 * @since 1.0.0
 */
@KapuaProvider
public class DeviceCommandManagementServiceImpl extends AbstractDeviceManagementServiceImpl implements DeviceCommandManagementService {

    @Override
    public DeviceCommandOutput exec(KapuaId scopeId, KapuaId deviceId, DeviceCommandInput commandInput, Long timeout)
            throws KapuaException {
        //
        // Argument Validation
        ArgumentValidator.notNull(scopeId, "scopeId");
        ArgumentValidator.notNull(deviceId, "deviceId");
        ArgumentValidator.notNull(commandInput, "commandInput");
        ArgumentValidator.notNull(commandInput.getTimeout(), "commandInput.timeout");

        //
        // Check Access
        AUTHORIZATION_SERVICE.checkPermission(PERMISSION_FACTORY.newPermission(DeviceManagementDomains.DEVICE_MANAGEMENT_DOMAIN, Actions.execute, scopeId));

        //
        // Prepare the request
        CommandRequestChannel commandRequestChannel = new CommandRequestChannel();
        commandRequestChannel.setAppName(CommandAppProperties.APP_NAME);
        commandRequestChannel.setVersion(CommandAppProperties.APP_VERSION);
        commandRequestChannel.setMethod(KapuaMethod.EXECUTE);

        CommandRequestPayload commandRequestPayload = new CommandRequestPayload();
        commandRequestPayload.setCommand(commandInput.getCommand());
        commandRequestPayload.setArguments(commandInput.getArguments());
        commandRequestPayload.setStdin(commandInput.getStdin());
        commandRequestPayload.setTimeout(commandInput.getTimeout());
        commandRequestPayload.setWorkingDir(commandInput.getWorkingDir());
        commandRequestPayload.setEnvironmentPairs(commandInput.getEnvironment());
        commandRequestPayload.setRunAsync(commandInput.isRunAsynch());
        commandRequestPayload.setPassword(commandInput.getPassword());
        commandRequestPayload.setBody(commandInput.getBody());

        CommandRequestMessage commandRequestMessage = new CommandRequestMessage();
        commandRequestMessage.setScopeId(scopeId);
        commandRequestMessage.setDeviceId(deviceId);
        commandRequestMessage.setCapturedOn(new Date());
        commandRequestMessage.setPayload(commandRequestPayload);
        commandRequestMessage.setChannel(commandRequestChannel);

        //
        // Do exec
        DeviceCallExecutor deviceApplicationCall = new DeviceCallExecutor(commandRequestMessage, timeout);
        CommandResponseMessage responseMessage = (CommandResponseMessage) deviceApplicationCall.send();

        //
        // Create event
        createDeviceEvent(scopeId, deviceId, commandRequestMessage, responseMessage);

        //
        // Check response
        if (responseMessage.getResponseCode().isAccepted()) {
            CommandResponsePayload responsePayload = responseMessage.getPayload();

            DeviceCommandOutput deviceCommandOutput = new DeviceCommandOutputImpl();
            deviceCommandOutput.setExceptionMessage(responsePayload.getExceptionMessage());
            deviceCommandOutput.setExceptionStack(responsePayload.getExceptionStack());
            deviceCommandOutput.setExitCode(responsePayload.getExitCode());
            deviceCommandOutput.setHasTimedout(false); // FIXME: implement track of timeout!!!
            deviceCommandOutput.setStderr(responsePayload.getStderr());
            deviceCommandOutput.setStdout(responsePayload.getStdout());

            return deviceCommandOutput;
        } else {
            KapuaResponsePayload responsePayload = responseMessage.getPayload();

            throw new CommandExecuteManagementException(responseMessage.getResponseCode(), responsePayload.getExceptionMessage(), responsePayload.getExceptionStack());
        }
    }
}
