package com.tyron.psi.lookup;

import android.graphics.drawable.Icon;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.psi.*;
import org.jetbrains.kotlin.com.intellij.psi.meta.PsiMetaData;
import org.jetbrains.kotlin.com.intellij.psi.util.PsiUtilCore;

/**
 * @author peter
 */
public class DefaultLookupItemRenderer extends LookupElementRenderer<LookupItem<?>>{
    public static final DefaultLookupItemRenderer INSTANCE = new DefaultLookupItemRenderer();

    @Override
    public void renderElement(LookupItem<?> item, LookupElementPresentation presentation) {
        presentation.setIcon(getRawIcon(item));

        presentation.setItemText(getName(item));
        presentation.setItemTextBold(item.getAttribute(LookupItem.HIGHLIGHTED_ATTR) != null);
        presentation.setTailText(getText2(item), item.getAttribute(LookupItem.TAIL_TEXT_SMALL_ATTR) != null);
        presentation.setTypeText(getText3(item), null);
    }

    /**
     * @deprecated use {@link #getRawIcon(LookupElement)}
     */
    @SuppressWarnings("unused")
    @Nullable
    @Deprecated
    public static Icon getRawIcon(final LookupElement item, boolean real) {
        return getRawIcon(item);
    }

    @Nullable
    public static Icon getRawIcon(final LookupElement item) {
        Icon icon = _getRawIcon(item);
//        if (icon instanceof ScalableIcon) icon = ((ScalableIcon)icon).scale(1f);
//        if (icon != null && icon.getIconHeight() > PlatformIcons.CLASS_ICON.getIconHeight()) {
//            return new SizedIcon(icon, icon.getIconWidth(), PlatformIcons.CLASS_ICON.getIconHeight());
//        }
        return icon;
    }

    @Nullable
    private static Icon _getRawIcon(LookupElement item) {
        if (item instanceof LookupItem) {
            Icon icon = (Icon)((LookupItem<?>)item).getAttribute(LookupItem.ICON_ATTR);
            if (icon != null) return icon;
        }

        Object o = item.getObject();

//        if (o instanceof Iconable && !(o instanceof PsiElement)) {
//            return ((Iconable)o).getIcon(Registry.is("ide.completion.show.visibility.icon") ? Iconable.ICON_FLAG_VISIBILITY : 0);
//        }
//
//        final PsiElement element = item.getPsiElement();
//        if (element != null && element.isValid()) {
//            return element.getIcon(Registry.is("ide.completion.show.visibility.icon") ? Iconable.ICON_FLAG_VISIBILITY : 0);
//        }
        return null;
    }


    @SuppressWarnings("deprecation")
    @Nullable
    private static String getText3(LookupItem<?> item) {
        Object o = item.getObject();
        String text;
//        if (o instanceof LookupValueWithUIHint) {
//            text = ((LookupValueWithUIHint)o).getTypeHint();
//        }
       // else {
            text = (String)item.getAttribute(LookupItem.TYPE_TEXT_ATTR);
       // }
        return text;
    }

    private static String getText2(LookupItem<?> item) {
        return (String)item.getAttribute(LookupItem.TAIL_TEXT_ATTR);
    }

    @SuppressWarnings("deprecation")
    private static String getName(LookupItem<?> item){
        final String presentableText = item.getPresentableText();
        if (presentableText != null) return presentableText;
        final Object o = item.getObject();
        String name = null;
        if (o instanceof PsiElement) {
            final PsiElement element = (PsiElement)o;
            if (element.isValid()) {
                name = PsiUtilCore.getName(element);
            }
        }
        else if (o instanceof PsiMetaData) {
            name = ((PsiMetaData)o).getName();
        }
//        else if (o instanceof PresentableLookupValue ) {
//            name = ((PresentableLookupValue)o).getPresentation();
//        }
        else {
            name = String.valueOf(o);
        }
        if (name == null){
            name = "";
        }

        return name;
    }

}
