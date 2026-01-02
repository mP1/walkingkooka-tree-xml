/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.tree.xml;

import org.w3c.dom.Node;
import walkingkooka.Cast;

import java.util.Objects;

@SuppressWarnings("lgtm[java/inconsistent-equals-and-hashcode]")
abstract class XmlTextNode extends XmlLeafNode {

    XmlTextNode(final Node characterData) {
        super(characterData);
    }

    public abstract XmlTextNode setText(String text);

    final XmlNode setText0(final String text) {
        Objects.requireNonNull(text, "text");

        return this.text().equals(text) ? this : this.replaceText(text);
    }

    /**
     * Factory only called when different text is passed to a set text method
     */
    private XmlNode replaceText(final String text) {
        final org.w3c.dom.CharacterData characterData = Cast.to(this.nodeCloneAll());
        characterData.setTextContent(text);
        return this.wrap0(characterData);
    }

    // Object..........................................................................................................

    @Override
    public final int hashCode() {
        return this.text().hashCode();
    }

    @Override //
    final boolean equals0(final XmlNode other) {
        return this.text().equals(other.text());
    }
}
