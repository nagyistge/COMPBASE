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

/**
 * Created by igorborisov on 01.10.14.
 */
public class VaadinVersion70x extends VaadinVersion {

    public VaadinVersion70x(){
        this.vaadinFileInfos = Arrays.asList(
                new VaadinFileInfo(VAADIN_SERVER_JAR, portalPath, 100, true),
                new VaadinFileInfo(VAADIN_CLIENT_JAR, vaadinClientJarsPath, 200, true),
                new VaadinFileInfo(VAADIN_THEMES_JAR, portalPath, 300),
                new VaadinFileInfo(VAADIN_THEME_COMPILER_JAR, portalPath, 400),
                new VaadinFileInfo(VAADIN_SHARED_JAR, portalPath, 500, true),
                new VaadinFileInfo(VAADIN_SHARED_DEPS_JAR, portalPath, 600, libDir, true),
                new VaadinFileInfo(VAADIN_CLIENT_COMPILER_JAR, vaadinClientJarsPath, 700, true),
                new VaadinFileInfo(JSOUP_JAR, portalPath, 800, libDir),
                new VaadinFileInfo(VALIDATON_API, portalPath, 900, libDir, true),
                new VaadinFileInfo(VALIDATON_API_SOURCES, portalPath, 1000, libDir, true),
                new VaadinFileInfo(ANT_JAR, vaadinClientJarsPath, 1100, libDir, true)
        );
    }
}
