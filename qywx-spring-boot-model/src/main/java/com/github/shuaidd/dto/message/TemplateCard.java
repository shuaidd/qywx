package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 企业微信模版卡片
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TemplateCard {

    @JsonProperty("card_type")
    private String cardType;

    private TemplateCardSource source;

    @JsonProperty("action_menu")
    private TemplateCardActionMenu actionMenu;

    @JsonProperty("main_title")
    private TemplateCardTitle mainTitle;

    @JsonProperty("quote_area")
    private TemplateCardQuoteArea quoteArea;

    @JsonProperty("emphasis_content")
    private TemplateCardTitle emphasisContent;

    @JsonProperty("sub_title_text")
    private String subTitleText;

    @JsonProperty("horizontal_content_list")
    private List<TemplateCardHorizontalContent> horizontalContentList;

    @JsonProperty("jump_list")
    private List<TemplateCardJump> jumpList;

    @JsonProperty("card_action")
    private TemplateCardActionLink cardAction;

    @JsonProperty("image_text_area")
    private TemplateCardImageTextArea imageTextArea;

    @JsonProperty("card_image")
    private TemplateCardImage cardImage;

    @JsonProperty("vertical_content_list")
    private List<TemplateCardVerticalContent> verticalContentList;

    @JsonProperty("button_selection")
    private TemplateCardSelection buttonSelection;

    @JsonProperty("button_list")
    private List<TemplateCardButton> buttonList;

    private TemplateCardCheckbox checkbox;

    @JsonProperty("select_list")
    private List<TemplateCardSelection> selectList;

    @JsonProperty("submit_button")
    private TemplateCardSubmitButton submitButton;

    @JsonProperty("replace_text")
    private String replaceText;

    private MessageFeedback feedback;

    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }
    public TemplateCardSource getSource() { return source; }
    public void setSource(TemplateCardSource source) { this.source = source; }
    public TemplateCardActionMenu getActionMenu() { return actionMenu; }
    public void setActionMenu(TemplateCardActionMenu actionMenu) { this.actionMenu = actionMenu; }
    public TemplateCardTitle getMainTitle() { return mainTitle; }
    public void setMainTitle(TemplateCardTitle mainTitle) { this.mainTitle = mainTitle; }
    public TemplateCardQuoteArea getQuoteArea() { return quoteArea; }
    public void setQuoteArea(TemplateCardQuoteArea quoteArea) { this.quoteArea = quoteArea; }
    public TemplateCardTitle getEmphasisContent() { return emphasisContent; }
    public void setEmphasisContent(TemplateCardTitle emphasisContent) { this.emphasisContent = emphasisContent; }
    public String getSubTitleText() { return subTitleText; }
    public void setSubTitleText(String subTitleText) { this.subTitleText = subTitleText; }
    public List<TemplateCardHorizontalContent> getHorizontalContentList() { return horizontalContentList; }
    public void setHorizontalContentList(List<TemplateCardHorizontalContent> horizontalContentList) { this.horizontalContentList = horizontalContentList; }
    public List<TemplateCardJump> getJumpList() { return jumpList; }
    public void setJumpList(List<TemplateCardJump> jumpList) { this.jumpList = jumpList; }
    public TemplateCardActionLink getCardAction() { return cardAction; }
    public void setCardAction(TemplateCardActionLink cardAction) { this.cardAction = cardAction; }
    public TemplateCardImageTextArea getImageTextArea() { return imageTextArea; }
    public void setImageTextArea(TemplateCardImageTextArea imageTextArea) { this.imageTextArea = imageTextArea; }
    public TemplateCardImage getCardImage() { return cardImage; }
    public void setCardImage(TemplateCardImage cardImage) { this.cardImage = cardImage; }
    public List<TemplateCardVerticalContent> getVerticalContentList() { return verticalContentList; }
    public void setVerticalContentList(List<TemplateCardVerticalContent> verticalContentList) { this.verticalContentList = verticalContentList; }
    public TemplateCardSelection getButtonSelection() { return buttonSelection; }
    public void setButtonSelection(TemplateCardSelection buttonSelection) { this.buttonSelection = buttonSelection; }
    public List<TemplateCardButton> getButtonList() { return buttonList; }
    public void setButtonList(List<TemplateCardButton> buttonList) { this.buttonList = buttonList; }
    public TemplateCardCheckbox getCheckbox() { return checkbox; }
    public void setCheckbox(TemplateCardCheckbox checkbox) { this.checkbox = checkbox; }
    public List<TemplateCardSelection> getSelectList() { return selectList; }
    public void setSelectList(List<TemplateCardSelection> selectList) { this.selectList = selectList; }
    public TemplateCardSubmitButton getSubmitButton() { return submitButton; }
    public void setSubmitButton(TemplateCardSubmitButton submitButton) { this.submitButton = submitButton; }
    public String getReplaceText() { return replaceText; }
    public MessageFeedback getFeedback() { return feedback; }
    public void setFeedback(MessageFeedback feedback) { this.feedback = feedback; }
    public void setReplaceText(String replaceText) { this.replaceText = replaceText; }
}
