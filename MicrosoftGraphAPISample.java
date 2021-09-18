class GraphAPIExample{
  
  private static final String GRAPH_URL="https://graph.microsoft.com/v1.0/users";
  private static final String AUTH="Authorization";
  private static final String BEAR="Bearer";
  
  RestTemplate restTemp = new RestTemplate();
  HttpHeaders headers = new HttpHeaders();
  MultiValueMap<String, String> map = new LinkedValueMap<>();
  HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
  
  try{
    map.add("grant_type", "client_credentials");
    map.add("client_id", clientId);
    map.add("client_secret", clientSecret);
    map.add("scope", "https://graph.microsoft.com/defaults");
    
    URI url=new URI("https://login.microsoftonline.com/" + tenantId + "/oauth2/v2.0/token");
    ResponseEntity<String> response = restTemp.postForEntity(url,request,String.class);
    
    String objects = response.getBody();
    Object obj = new JSONParser().parse(objects);
    JSONObject jo = (JSONObject)obj;
    String accessToken = (String)jo.get("access_token");
    
    String readMailURL = GRAPH_URL + username + "/mailFolders/" + folderId + "/Messages?$filter=isRead eq false";
    HttpHeaders header1 = new HttpHeaders();
    header1.setContentType(MediaType.APPLICATION_JSON);
    header1.add(AUTH, BEAR + accessToken);
    HttpEntity<String> request1 = new HttpEntity<>(header1);
    ResponseEntity<String> response1 = restTemp.exchange(readMailURL, HttpMethod.GET, request1, String.class);
    String objects1 = response1.getBody();
    Object obj1 = new JSONParser().parse(objects1);
    JSONObject jo1 = (JSONObject)obj1;
    JSONArray arr = (JSONArray)jo1.get("value");
    
    for(int i=0; i<arr.size(); i++){
      JSONObject jco1 = (JSONObject)arr.get(i);
      String subject = jco1.get("subject").toString();
      String messageId = jco1.get("id").toString();
      JSONObject bodyObj = (JSONObject)jco1.get("body");
      String body = bodyObj.get("content").toString();
      readReplyMail(username, subject, body, messageId, accessToken);
    }
  }catch(Exception e){
    log.error("Exception in processing mails");
  }
  
}
