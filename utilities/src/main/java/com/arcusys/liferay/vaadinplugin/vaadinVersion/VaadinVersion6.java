package com.arcusys.liferay.vaadinplugin.vaadinVersion;

/*
 * #%L
 * Liferay Vaadin Plugin
 * %%
 * Copyright (C) 2013 - 2014 Arcusys Ltd.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.arcusys.liferay.vaadinplugin.util.VaadinFileInfo;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by igorborisov on 01.10.14.
 */
public class VaadinVersion6 extends VaadinVersion {

    private static final String VAADIN_JAR = "vaadin.jar";

    @Override
    public Collection<VaadinFileInfo> getVaadinFilesInfo(){

        vaadinFileInfos = Arrays.asList(
                new VaadinFileInfo(VAADIN_JAR, portalPath, 100)
        );

        return vaadinFileInfos;
    }
}
