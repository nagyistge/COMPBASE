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

import com.arcusys.liferay.vaadinplugin.util.ControlPanelPortletUtil;
import com.arcusys.liferay.vaadinplugin.util.VaadinFileInfo;
import com.arcusys.liferay.vaadinplugin.util.Version;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by igorborisov on 01.10.14.
 */
public class VaadinVersion {

    protected Collection<VaadinFileInfo> vaadinFileInfos;
    protected Collection<VaadinFileInfo> vaadinFileCompilationInfos;


    String portalPath = ControlPanelPortletUtil.getPortalLibLocationPath();
    String vaadinClientJarsPath = ControlPanelPortletUtil.getVaadinClientJarsLocationPath();
    String libDir = ControlPanelPortletUtil.FileSeparator + "lib" + ControlPanelPortletUtil.FileSeparator;

    public Collection<VaadinFileInfo> getVaadinFilesInfo(){
        return vaadinFileInfos;
    }

    public Collection<File> getVaadinFileInfoForCompilation(){

        Collection<File> files = new ArrayList<File>();
        for(VaadinFileInfo info: this.vaadinFileInfos){
            if(info.getNeedForCompilation()) {
                files.add(info.getLocation());
            }
        }

        return files;
    }

    public static VaadinVersion getVaadinVersion(String vaadinVersion){
        Version currentVersion = new Version(vaadinVersion);
        return getVaadinVersion(currentVersion);
    }

    public static VaadinVersion getVaadinVersion(Version vaadinVersion){

        Version version600 = new Version("6.0.0");
        Version version700 = new Version("7.0.0");
        Version version710 = new Version("7.1.0");
        Version version720 = new Version("7.2.0");
        Version version730 = new Version("7.3.0");

        if (vaadinVersion.compareTo(version600) >= 0 && vaadinVersion.compareTo(version700) < 0) {
            return new VaadinVersion6();
        }else if (vaadinVersion.compareTo(version700) >= 0 && vaadinVersion.compareTo(version710) < 0)  {
            return new VaadinVersion70x();
        }else if (vaadinVersion.compareTo(version710) >= 0 && vaadinVersion.compareTo(version720) < 0)  {
            return new VaadinVersion71x();
        }else if (vaadinVersion.compareTo(version720) >= 0 && vaadinVersion.compareTo(version730) < 0)  {
            return new VaadinVersion72x();
        }else if (vaadinVersion.compareTo(version730) >= 0)  {
            return new VaadinVersion73x();
        }else{
            throw new IllegalArgumentException("Unsupported vaadin version");
        }
    }

    protected static final String ANT_JAR = "ant.jar";

    protected static final String VAADIN_SERVER_JAR = "vaadin-server.jar";
    protected static final String VAADIN_CLIENT_JAR = "vaadin-client.jar";
    protected static final String VAADIN_THEMES_JAR = "vaadin-themes.jar";
    protected static final String VAADIN_THEME_COMPILER_JAR = "vaadin-theme-compiler.jar";
    protected static final String VAADIN_SHARED_JAR = "vaadin-shared.jar";
    protected static final String VAADIN_SHARED_DEPS_JAR = "vaadin-shared-deps.jar";
    protected static final String VAADIN_CLIENT_COMPILER_JAR = "vaadin-client-compiler.jar";

    protected static final String JSOUP_JAR = "jsoup.jar";
    protected static final String VALIDATON_API = "validation-api.GA.jar";
    protected static final String VALIDATON_API_SOURCES = "validation-api.GA-sources.jar";

    protected static final String VAADIN_PUSH_JAR = "vaadin-push.jar";
    protected static final String VAADIN_CLIENT_COMPILER_DEPS_JAR = "vaadin-client-compiler-deps.jar";

    protected static final String VAADIN_SASS_COMPILER_JAR = "vaadin-sass-compiler.jar";

    protected static final String GUAVA_JAR = "guava.vaadin1.jar";
    protected static final String STREAM_HTML_PARSER_JSILVER_JAR = "streamhtmlparser-jsilver.vaadin1.jar";

    protected static final String SERIALIZER_JAR = "serializer.jar";

    protected static final String FLUTE_JAR = "flute.jar";
    protected static final String JSON_JAR = "json.jar";
    protected static final String SAC_JAR = "sac.jar";
}
