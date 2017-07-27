package com.github.kongchen.swagger.docgen;

import io.swagger.models.Path;
import io.swagger.models.Tag;

import java.util.*;

public class CustomSwagger extends io.swagger.models.Swagger {


	private String[] tagSorted;

	public Map<String, Path> getPaths() {
        if (this.paths == null) {
            return null;
        } else {

        	final List<Map.Entry<String, Path>> sortedPaths = new ArrayList<Map.Entry<String, Path>>();
        	sortedPaths.addAll(paths.entrySet());
			Collections.sort(sortedPaths, getPathComparator());

			final Map<String, Path> result = new LinkedHashMap<String, Path>();
			for (Map.Entry<String, Path> sortedPath : sortedPaths) {
				result.put(sortedPath.getKey(), sortedPath.getValue());
			}

			return result;
		}
    }

	private Comparator<Map.Entry<String, Path>> getPathComparator() {
		return new Comparator<Map.Entry<String, Path>>() {

			@Override
			public int compare(Map.Entry<String, Path> o1, Map.Entry<String, Path> o2) {

				//Only the first operation will define the priority
				CustomOperation operation1 = (CustomOperation) o1.getValue().getOperations().get(0);
				CustomOperation operation2 = (CustomOperation) o2.getValue().getOperations().get(0);
				if (operation1.getPriority() == operation2.getPriority() ) return 0;
				return operation1.getPriority() > operation2.getPriority() ? -1 : 1;
			}
		};
	}

	public void setTagSorted(String[] tagSorted) {
		this.tagSorted = tagSorted;
	}

	public String[] getTagSorted() {
		return tagSorted;
	}

	@Override
	public List<Tag> getTags() {

		final List<Tag> result = new ArrayList<Tag>();
		final List<Tag> tags = super.getTags();

		for (String tagName : tagSorted) {

			for (Tag tag : tags) {
				if (tag.getName().equals(tagName)) {
					result.add(tag);
				}
			}

		}

		return result;
	}
}
