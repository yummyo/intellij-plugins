// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.coldFusion.UI.editorActions;

import com.intellij.coldFusion.model.CfmlUtil;
import com.intellij.coldFusion.model.psi.CfmlTag;
import com.intellij.coldFusion.model.psi.impl.CfmlAttributeImpl;
import com.intellij.lang.documentation.DocumentationProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;

import java.util.List;

/**
 * Created by Lera Nikolaenko
 */
public class CfmlDocumentProvider implements DocumentationProvider {
  @Override
  public String getQuickNavigateInfo(PsiElement element, PsiElement originalElement) {
    return null;
  }

  @Override
  public List<String> getUrlFor(PsiElement element, PsiElement originalElement) {
    return null;
  }

  @Override
  public String generateDoc(PsiElement element, PsiElement originalElement) {
    if (element instanceof CfmlAttributeImpl && element.getParent() instanceof CfmlTag) {
      return CfmlUtil.getAttributeDescription(((CfmlTag)element.getParent()).getTagName().toLowerCase(),
                                                       ((CfmlAttributeImpl)element).getName().toLowerCase(), element.getProject());
    }
    else if (element instanceof CfmlTag) {
      String name = ((CfmlTag)element).getTagName().toLowerCase();
      if (CfmlUtil.isStandardTag(name, element.getProject())) {
        return CfmlUtil.getTagDescription(name, element.getProject());
      }
    }
    return null;
  }

  @Override
  public PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object object, PsiElement element) {
    return null;
  }

  @Override
  public PsiElement getDocumentationElementForLink(PsiManager psiManager, String link, PsiElement context) {
    return null;
  }
}
