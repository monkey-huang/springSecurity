# 第一次讀書會demo之成果

# 爾後探討內容
* filter的流程概念
* token與加密
* 實作provider manager
* 探討credential

# 一、專案內容
* 將驗證功能交由spring security的框架去處理
* 以微服務的功能去驗證使用者，並創建該使用者的token放置redis
* 依照通過驗證的使用者之權限，配對相對應的功能
* 有login功能和lougout功能，並可以客製化回覆的內容
