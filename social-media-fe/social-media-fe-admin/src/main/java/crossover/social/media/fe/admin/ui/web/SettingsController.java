package crossover.social.media.fe.admin.ui.web;

import crossover.social.media.domain.Company;
import crossover.social.media.domain.Setting;
import crossover.social.media.domain.SettingType;
import crossover.social.media.fe.admin.ui.business.SettingManager;
import crossover.social.media.fe.admin.ui.web.model.PluginData;
import crossover.social.media.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SettingsController
 * Created by thebaz on 12/04/15.
 */
@Controller(value = "settingsController")
public class SettingsController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final List<Setting> settings = new ArrayList<>();
    private final List<PluginData> plugins = new ArrayList<>();
    @Autowired
    private SettingManager settingManager;

    @ModelAttribute("company")
    Company company() {
        final Company company = new Company();
        company.setName("Acme Corp.");
        return company;
    }

    @ModelAttribute("plugins")
    public List<PluginData> allPlugins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        loadSettings(request, response);
        return plugins;
    }

    @ModelAttribute("allSettings")
    public List<Setting> allSites(HttpServletRequest request, HttpServletResponse response) throws IOException {
        loadSettings(request, response);
        return settings;
    }

    private void loadSettings(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (plugins.isEmpty() && settings.isEmpty()) {
            Map<String, Setting> settingMap = new HashMap<>();
            for (Setting setting : settingManager.findSettings()) {
                settingMap.put(setting.getKey(), setting);
            }

            final Map<String, Plugin> pluginMap = settingManager.findPlugins();
            for (Map.Entry<String, Plugin> entry : pluginMap.entrySet()) {
                logger.debug("Load setting bean {}", entry.getKey());
                Plugin plugin = entry.getValue();
                PluginData pluginData = new PluginData(plugin.getId(), plugin.getName(), plugin.getType().toString(), plugin.getStatus().toString());
                for (Setting setting : plugin.getSettings()) {
                    Setting matched = settingMap.remove(setting.getKey());
                    if (setting.getKey().endsWith("activate")) {
                        pluginData.setActivate(matched);
                    } else {
                        pluginData.getSettings().add(matched);
                    }
                }
                plugins.add(pluginData);
            }
            settings.addAll(settingMap.values());
        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @RequestMapping(value = {"/settings/plugin/activate/{pluginId}"}, method = RequestMethod.GET)
    public String activatePlugin(HttpServletRequest request, HttpServletResponse response, @PathVariable("pluginId") String pluginId) throws IOException {
        PluginData pluginData = plugins.stream().filter(p -> p.getId().equals(pluginId)).findFirst().get();
        Setting setting = pluginData.getActivate();
        Setting editableSetting = settingManager.findSetting(setting.getId());
        editableSetting.setValue(Boolean.TRUE);
        settingManager.editSetting(editableSetting);

        plugins.stream().filter(p -> p.getType().equals(pluginData.getType()) && !p.getId().equals(pluginId)).forEach(p -> {
            Setting aSetting = settingManager.findSetting(p.getActivate().getId());
            aSetting.setValue(Boolean.FALSE);
            settingManager.editSetting(aSetting);
        });

        return reload(request, response);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @RequestMapping(value = {"/settings/plugin/deactivate/{pluginId}"}, method = RequestMethod.GET)
    public String deactivatePlugin(HttpServletRequest request, HttpServletResponse response, @PathVariable("pluginId") String pluginId) throws IOException {
        PluginData pluginData = plugins.stream().filter(p -> p.getId().equals(pluginId)).findFirst().get();
        Setting setting = pluginData.getActivate();
        Setting editableSetting = settingManager.findSetting(setting.getId());
        editableSetting.setValue(Boolean.FALSE);
        settingManager.editSetting(editableSetting);

        return reload(request, response);
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @RequestMapping(value = {"/settings/reload"}, method = RequestMethod.GET)
    public String reload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        settingManager.reloadSettings();
        settings.clear();
        plugins.clear();
        loadSettings(request, response);
        return "redirect:/settings";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @RequestMapping(value = {"/settings"}, method = RequestMethod.GET)
    public String show(ModelMap model) {
        model.put("setting", new Setting());
        return "settings";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @RequestMapping(value = {"/settings/{settingId}"}, method = RequestMethod.GET)
    public String editMode(ModelMap model, @PathVariable("settingId") String settingId) throws IOException {
        Setting setting = settingManager.findSetting(settingId);
        model.put("setting", setting);
        return "settings";
    }

    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    @RequestMapping(value = {"/settings"}, method = RequestMethod.PUT)
    public String editSetting(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("setting") Setting setting,
                              final BindingResult bindingResult, final ModelMap model) throws IOException {
        if (bindingResult.hasErrors()) {
            return "settings";
        }
        Setting editableSetting = settingManager.findSetting(setting.getId());
        editableSetting.setValue(getTypedValue((String) setting.getValue(), setting.getType()));
        settingManager.editSetting(editableSetting);
        model.clear();
        return reload(request, response);
    }

    private Object getTypedValue(String value, SettingType type) {
        switch (type) {
            case BOOL:
                return Boolean.parseBoolean(value);
            case INTEGER:
                return Integer.parseInt(value);
            case DOUBLE:
                return Double.parseDouble(value);
            default:
                return value;
        }
    }
}
