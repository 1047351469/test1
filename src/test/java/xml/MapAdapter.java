package xml;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.*;

import java.util.*;

public class MapAdapter extends XmlAdapter<MapAdapter.AdaptedMap, Map<String, String>> {

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedMap {
        @XmlElement(name = "entry")
        public List<Entry> entries = new ArrayList<>();
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Entry {
        @XmlAttribute
        public String key;

        @XmlValue
        public String value;
    }

    @Override
    public Map<String, String> unmarshal(AdaptedMap adaptedMap) {
        Map<String, String> map = new HashMap<>();
        for (Entry entry : adaptedMap.entries) {
            map.put(entry.key, entry.value);
        }
        return map;
    }

    @Override
    public AdaptedMap marshal(Map<String, String> map) {
        AdaptedMap adaptedMap = new AdaptedMap();
        for (Map.Entry<String, String> e : map.entrySet()) {
            Entry entry = new Entry();
            entry.key = e.getKey();
            entry.value = e.getValue();
            adaptedMap.entries.add(entry);
        }
        return adaptedMap;
    }
}
