package crossover.social.media.plugin.search;

import crossover.social.media.plugin.Plugin;

import java.util.List;

/**
 * SearchPlugin
 * Created by bazzoni
 */
public interface SearchPlugin<T extends SearchDocument> extends Plugin {
    /**
     * Update a content in index
     *
     * @param id      document id
     * @param siteId  document siteId
     * @param name    document name
     * @param uri     document uri
     * @param date    document date
     * @param summary document summary
     * @param content document content
     */
    void addToIndex(String id, String siteId, String name, String uri, Long date, String summary, String content);

    /**
     * Delete an indexed document from Solr index
     *
     * @param id document id
     */
    void deleteFromIndex(String id);

    /**
     * Search index for specified term
     *
     * @param searchTerm search term
     * @param index search index
     * @return found documents
     */
    List<T> search(String searchTerm, SearchIndex index);

    /**
     * Update a content in index
     *
     * @param id      document id
     * @param siteId  document siteId
     * @param name    document name
     * @param uri     document uri
     * @param date    document date
     * @param summary document summary
     * @param content document content
     */
    void update(String id, String siteId, String name, String uri, Long date, String summary, String content);
}
