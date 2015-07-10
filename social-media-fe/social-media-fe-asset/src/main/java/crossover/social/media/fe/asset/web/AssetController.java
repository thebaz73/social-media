package crossover.social.media.fe.asset.web;

import crossover.social.media.domain.SocialAsset;
import crossover.social.media.plugin.PluginOperationException;
import crossover.social.media.plugin.asset.Asset;
import crossover.social.media.plugin.service.PluginService;
import crossover.social.media.repository.AssetRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static crossover.social.media.plugin.asset.AssetUtils.findContentTypeByFileName;

/**
 * AssetController
 * Created by bazzoni on 10/07/2015.
 */
@RestController
@RequestMapping(value = "/api")
public class AssetController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AssetRepository cmsAssetRepository;
    @Autowired
    private PluginService pluginService;

    @RequestMapping(value = "/assets/**", method = RequestMethod.GET)
    public void assets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            final String prefix = "/api/assets/";
            final String requestURI = request.getRequestURI();
            String uri = requestURI.substring(prefix.length());
            Asset asset = findAssetByUri(uri);
            if (asset != null) {
                if (asset.getContent() == null) {
                    response.setHeader("Location", asset.getUri());
                    response.sendRedirect(asset.getUri());
                } else {
                    String mimeType = findContentTypeByFileName(uri.substring(uri.lastIndexOf("/") + 1));
                    response.setContentType(mimeType);
                    response.setHeader("X-Frame-Options", "SAMEORIGIN");

                    ByteArrayInputStream is = new ByteArrayInputStream(asset.getContent());
                    IOUtils.copy(is, response.getOutputStream());
                    response.flushBuffer();
                }
            }
        } catch (Exception e) {
            String msg = String.format("Cannot manage assets. Reason: %s",
                    e.getMessage());
            logger.info(msg, e);
            response.sendError(400, msg);
        }
    }

    private Asset findAssetByUri(String uri) throws PluginOperationException {
        final List<SocialAsset> bySiteIdAndUri = cmsAssetRepository.findBySiteIdAndUri(uri.substring(0, uri.indexOf("/")), uri);
        if (!bySiteIdAndUri.isEmpty()) {
            SocialAsset cmsAsset = bySiteIdAndUri.get(0);
            return pluginService.getAssetManagementPlugin().findAsset(cmsAsset.getSiteId(), "", uri.substring(uri.lastIndexOf("/") + 1));
        }
        return null;
    }
}
