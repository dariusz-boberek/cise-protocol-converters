package eu.cise.ivef;

import eu.cise.datamodel.v1.entity.metadata.Metadata;
import eu.cise.ivef.generated.TaggedItem;

import java.util.List;

import static eu.cise.ivef.Constants.METADATA_CUSTOM_KV_DELIMITER;

public class ConversionUtils {



    public static String getValueFromTaggedItem(String ivefTaggedItemKey, List<TaggedItem> taggedItems) {
        for (TaggedItem taggedItem : taggedItems) {
            if (taggedItem.getKey().equals(ivefTaggedItemKey)) {
                return taggedItem.getValue();
            }
        }
        return null;
    }


    public static String getValueFromCiseMetadata(List<Metadata> metadataList, String key) {
        Metadata foundMetadata = null;
        for (Metadata metadata : metadataList) {
            if (metadata.getComments() == null) {
                continue;
            }
            if (metadata.getComments().startsWith(key + METADATA_CUSTOM_KV_DELIMITER)) {
                foundMetadata = metadata;
                break;
            }
        }
        if (foundMetadata != null) {
            return foundMetadata.getComments().split(METADATA_CUSTOM_KV_DELIMITER)[1];
        } else {
            return null;
        }
    }
}
