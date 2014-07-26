package com.github.masahirosuzuka.phonegapguidesigner.MainView;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Iconable;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.meta.PsiMetaData;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlTag;
import com.intellij.psi.xml.XmlTagChild;
import com.intellij.psi.xml.XmlTagValue;
import com.intellij.util.IncorrectOperationException;
import com.intellij.xml.XmlElementDescriptor;
import com.intellij.xml.XmlNSDescriptor;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 * Created by Masahiro Suzuka on 2014/07/20.
 */
public class ButtonTag implements XmlTag{

  public ButtonTag() {

  }

  @NotNull
  @Override
  public Project getProject() throws PsiInvalidElementAccessException {
    return null;
  }

  @NotNull
  @Override
  public Language getLanguage() {
    return null;
  }

  @Override
  public PsiManager getManager() {
    return null;
  }

  @NotNull
  @Override
  public PsiElement[] getChildren() {
    return new PsiElement[0];
  }

  @Override
  public PsiElement getParent() {
    return null;
  }

  @Override
  public PsiElement getFirstChild() {
    return null;
  }

  @Override
  public PsiElement getLastChild() {
    return null;
  }

  @Override
  public PsiElement getNextSibling() {
    return null;
  }

  @Override
  public PsiElement getPrevSibling() {
    return null;
  }

  @Override
  public PsiFile getContainingFile() throws PsiInvalidElementAccessException {
    return null;
  }

  @Override
  public TextRange getTextRange() {
    return null;
  }

  @Override
  public int getStartOffsetInParent() {
    return 0;
  }

  @Override
  public int getTextLength() {
    return 0;
  }

  @Nullable
  @Override
  public PsiElement findElementAt(int i) {
    return null;
  }

  @Nullable
  @Override
  public PsiReference findReferenceAt(int i) {
    return null;
  }

  @Override
  public int getTextOffset() {
    return 0;
  }

  @Override
  public String getText() {
    return null;
  }

  @NotNull
  @Override
  public char[] textToCharArray() {
    return new char[0];
  }

  @Override
  public PsiElement getNavigationElement() {
    return null;
  }

  @Override
  public PsiElement getOriginalElement() {
    return null;
  }

  @Override
  public boolean textMatches(@NotNull @NonNls CharSequence charSequence) {
    return false;
  }

  @Override
  public boolean textMatches(@NotNull PsiElement psiElement) {
    return false;
  }

  @Override
  public boolean textContains(char c) {
    return false;
  }

  @Override
  public void accept(@NotNull PsiElementVisitor psiElementVisitor) {

  }

  @Override
  public void acceptChildren(@NotNull PsiElementVisitor psiElementVisitor) {

  }

  @Override
  public PsiElement copy() {
    return null;
  }

  @Override
  public PsiElement add(@NotNull PsiElement psiElement) throws IncorrectOperationException {
    return null;
  }

  @Override
  public PsiElement addBefore(@NotNull PsiElement psiElement, @Nullable PsiElement psiElement2) throws IncorrectOperationException {
    return null;
  }

  @Override
  public PsiElement addAfter(@NotNull PsiElement psiElement, @Nullable PsiElement psiElement2) throws IncorrectOperationException {
    return null;
  }

  @Override
  public void checkAdd(@NotNull PsiElement psiElement) throws IncorrectOperationException {

  }

  @Override
  public PsiElement addRange(PsiElement psiElement, PsiElement psiElement2) throws IncorrectOperationException {
    return null;
  }

  @Override
  public PsiElement addRangeBefore(@NotNull PsiElement psiElement, @NotNull PsiElement psiElement2, PsiElement psiElement3) throws IncorrectOperationException {
    return null;
  }

  @Override
  public PsiElement addRangeAfter(PsiElement psiElement, PsiElement psiElement2, PsiElement psiElement3) throws IncorrectOperationException {
    return null;
  }

  @Override
  public void delete() throws IncorrectOperationException {

  }

  @Override
  public void checkDelete() throws IncorrectOperationException {

  }

  @Override
  public void deleteChildRange(PsiElement psiElement, PsiElement psiElement2) throws IncorrectOperationException {

  }

  @Override
  public PsiElement replace(@NotNull PsiElement psiElement) throws IncorrectOperationException {
    return null;
  }

  @Override
  public boolean isValid() {
    return false;
  }

  @Override
  public boolean isWritable() {
    return false;
  }

  @Nullable
  @Override
  public PsiReference getReference() {
    return null;
  }

  @NotNull
  @Override
  public PsiReference[] getReferences() {
    return new PsiReference[0];
  }

  @Nullable
  @Override
  public <T> T getCopyableUserData(Key<T> tKey) {
    return null;
  }

  @Override
  public <T> void putCopyableUserData(Key<T> tKey, @Nullable T t) {

  }

  @Override
  public boolean processDeclarations(@NotNull PsiScopeProcessor psiScopeProcessor, @NotNull ResolveState resolveState, @Nullable PsiElement psiElement, @NotNull PsiElement psiElement2) {
    return false;
  }

  @Nullable
  @Override
  public PsiElement getContext() {
    return null;
  }

  @Override
  public boolean isPhysical() {
    return false;
  }

  @NotNull
  @Override
  public GlobalSearchScope getResolveScope() {
    return null;
  }

  @NotNull
  @Override
  public SearchScope getUseScope() {
    return null;
  }

  @Override
  public ASTNode getNode() {
    return null;
  }

  @Override
  public String toString() {
    return null;
  }

  @Override
  public boolean isEquivalentTo(PsiElement psiElement) {
    return false;
  }

  @NotNull
  @Override
  public String getName() {
    return null;
  }

  @Override
  public PsiElement setName(@NonNls @NotNull String s) throws IncorrectOperationException {
    return null;
  }

  @NotNull
  @Override
  public String getNamespace() {
    return null;
  }

  @NotNull
  @Override
  public String getLocalName() {
    return null;
  }

  @Nullable
  @Override
  public XmlElementDescriptor getDescriptor() {
    return null;
  }

  @NotNull
  @Override
  public XmlAttribute[] getAttributes() {
    return new XmlAttribute[0];
  }

  @Nullable
  @Override
  public XmlAttribute getAttribute(@NonNls String s, @NonNls String s2) {
    return null;
  }

  @Nullable
  @Override
  public XmlAttribute getAttribute(@NonNls String s) {
    return null;
  }

  @Nullable
  @Override
  public String getAttributeValue(@NonNls String s, @NonNls String s2) {
    return null;
  }

  @Nullable
  @Override
  public String getAttributeValue(@NonNls String s) {
    return null;
  }

  @Override
  public XmlAttribute setAttribute(@NonNls String s, @NonNls String s2, @NonNls String s3) throws IncorrectOperationException {
    return null;
  }

  @Override
  public XmlAttribute setAttribute(@NonNls String s, @NonNls String s2) throws IncorrectOperationException {
    return null;
  }

  @Override
  public XmlTag createChildTag(@NonNls String s, @NonNls String s2, @Nullable @NonNls String s3, boolean b) {
    return null;
  }

  @Override
  public XmlTag addSubTag(XmlTag xmlTag, boolean b) {
    return null;
  }

  @NotNull
  @Override
  public XmlTag[] getSubTags() {
    return new XmlTag[0];
  }

  @NotNull
  @Override
  public XmlTag[] findSubTags(@NonNls String s) {
    return new XmlTag[0];
  }

  @NotNull
  @Override
  public XmlTag[] findSubTags(@NonNls String s, @Nullable String s2) {
    return new XmlTag[0];
  }

  @Nullable
  @Override
  public XmlTag findFirstSubTag(@NonNls String s) {
    return null;
  }

  @NotNull
  @Override
  public String getNamespacePrefix() {
    return null;
  }

  @NotNull
  @Override
  public String getNamespaceByPrefix(@NonNls String s) {
    return null;
  }

  @Nullable
  @Override
  public String getPrefixByNamespace(@NonNls String s) {
    return null;
  }

  @Override
  public String[] knownNamespaces() {
    return new String[0];
  }

  @Override
  public boolean hasNamespaceDeclarations() {
    return false;
  }

  @NotNull
  @Override
  public Map<String, String> getLocalNamespaceDeclarations() {
    return null;
  }

  @NotNull
  @Override
  public XmlTagValue getValue() {
    return null;
  }

  @Nullable
  @Override
  public XmlNSDescriptor getNSDescriptor(@NonNls String s, boolean b) {
    return null;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public void collapseIfEmpty() {

  }

  @Nullable
  @Override
  public String getSubTagText(@NonNls String s) {
    return null;
  }

  @Nullable
  @Override
  public XmlTag getParentTag() {
    return null;
  }

  @Nullable
  @Override
  public XmlTagChild getNextSiblingInTag() {
    return null;
  }

  @Nullable
  @Override
  public XmlTagChild getPrevSiblingInTag() {
    return null;
  }

  @Override
  public boolean processElements(PsiElementProcessor psiElementProcessor, PsiElement psiElement) {
    return false;
  }

  @Override
  public Icon getIcon(@IconFlags int i) {
    return null;
  }

  @Nullable
  @Override
  public PsiMetaData getMetaData() {
    return null;
  }

  @Nullable
  @Override
  public <T> T getUserData(@NotNull Key<T> tKey) {
    return null;
  }

  @Override
  public <T> void putUserData(@NotNull Key<T> tKey, @Nullable T t) {

  }
}
