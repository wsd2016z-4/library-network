package wsd.mirex.elibrary.message;

import org.w3c.dom.Element;

/**
 * Created by pj on 18.12.16.
 */

/** Interfejs deserializacji */
public interface XmlDeserializable<T>
{
    public T deserialize(Element element) throws Exception;
}
