
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%
    int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    final int MAX_REQUEST_SIZE = 1024 * 1024;
    DiskFileItemFactory factory = new DiskFileItemFactory();
    // Sets the size threshold beyond which files are written directly to disk.
    factory.setSizeThreshold(MAX_MEMORY_SIZE);
    // Sets the directory used to temporarily store files that are larger
    // than the configured size threshold. We use temporary directory for java
    factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
    // constructs the folder where uploaded file will be stored
    String uploadFolder = "F:/1-12-2015/MetaSocio/WebContent/assets/img/people";
    // Create a new file upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);
    // Set overall request size constraint
    upload.setSizeMax(MAX_REQUEST_SIZE);
    try {
        // Parse the request
        List items = upload.parseRequest(request);
        for (Object item1 : items) {
        	System.out.print("D");
            FileItem item = (FileItem) item1;
            if (!item.isFormField()) {
                String fileName = new File(item.getName()).getName();
                String filePath = uploadFolder + File.separator + fileName;
                File uploadedFile = new File(filePath);
                // saves the file to upload directory
                item.write(uploadedFile);
            }
        }
        response.sendRedirect("success.jsp");
    } catch (Exception e) {
    	System.out.print(e);
        response.sendRedirect("error.jsp");
    }
%>
<html>
<head>
    <title>File Processor</title>
</head>
<body>
<%@ page session="false"%>
 <%@  page errorPage="../../exception/error.jsp"%>
</body>
</html>