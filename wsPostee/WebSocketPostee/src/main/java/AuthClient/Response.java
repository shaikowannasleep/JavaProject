/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuthClient;

/**
 *
 * @author Hoang Quoc Bao
 */
public class Response {
    public int code;
    public String type;
    public String message;
    public Object data;
    public long t;
    
    public Response()
    {
        
    }
    public Response(int code, String message,Object data, String type)
    {
        this.code = code;
        this.type=type;
        this.message = message;
        this.t = System.currentTimeMillis()/1000L;
        this.data = data;
    }
    public static Response getErrorCode(ErrorCode errorCode, String type)
    {
        switch(errorCode)
        {
            case USERNAME_EXIST: return new Response(901,"Tài khoản đã tồn tại, vui lòng chọn tài khoản khác!",null, type);
            case USERNAME_PASSWORD_FORMAT: return new Response(902,"Mật khẩu của bạn phải lớn hơn 8 ký tự!",null, type);
            case EMAIL_EXIST: return new Response(903,"Email đã tồn tại, vui lòng chọn email khác!",null, type);
            case USER_UNVERIFY: return new Response(904,"Tài khoản của bạn chưa được xác thực, vui lòng xác thực tài khoản trên email để hoàn tất quá trình đăng ký!",null, type);
            case USERNAME_FORMAT: return new Response(905,"Tài khoản của bạn chỉ được chứa các ký tự số, chữ, hoặc dấu '.', vui lòng thử lại.",null, type);
            case USERNAME_LENGTH: return new Response(906,"Tài khoản của bạn phải có ít nhất 5 ký tự, vui lòng thử lại.",null, type);
            case EMAIL_ERROR: return new Response(907,"Vui lòng nhập đúng email",null, type);
            case PHONE_ERROR: return new Response(908,"Vui lòng nhập đúng email",null, type);
            case ADDRESS_ERROR: return new Response(909,"Địa chỉ của bạn không được để trống",null, type);
            case DATEOFBIRTH_ERROR: return new Response(910,"Vui lòng nhập ngày sinh",null, type);
            case GENDER_ERROR: return new Response(910,"Vui lòng nhập giới tính",null, type);
            case TIME_OUT: return new Response (911,"Phiên đăng ký không khả dụng hoặc hết hạn.", null, type);
            case ACCOUNT_NOT_EXIST: return new Response (801,"Tài khoản không tồn tại hoặc mật khẩu không đúng, vui lòng thử lại!", null, type);
            case UNKNOW_ERROR: return new Response (1001,"Lỗi không xác định, vui lòng thử lại sau", null,type);
            case PERMISSION_LIMIT: return new Response (1101,"Bạn không thể search trường này", null,type);
            case PERMISSION_ADMIN: return new Response (1102,"Bạn không thể sử dụng tính năng này trường này", null,type);
            case JOB_NULL: return new Response (912,"Ngành nghề (job) không thể bỏ trống", null,type);
            case UNAUTHORIZATION_TOKEN: return new Response (913,"Phiên đăng nhập không khả dụng hoặc đã hết hạn", null,type);
            case NO_IMAGE: return new Response (914,"Không có ảnh trên hệ thống", null,type);
            case LINK_EXPIRED: return new Response (1501,"Link đã hết hạn, vui lòng reset link mới",null,type);
        }
        return null;
    }
    //NO_IMAGE
    public static String stringDone(CodeDone errorCode)
    {
        switch(errorCode)
        {
            case GET_INFOR_SUCCESS: return "Lấy thông tin thành công";
            case LOGIN_SUCCESS: return "Đăng nhập thành công!";
        }
        return null;
    }
    public static Response getDataSuccess(CodeDone codeDone, String type,Object data)
    {
        switch(codeDone)
        {
            case GET_DATA_SUCCESS: return new Response(200,"Lấy dữ liệu thành công", data,type);
            case REMOVE_UID_SUCCESS: return new Response(200,"Xoá người dùng thành công", data,type);
            case UPDATE_IMGPROFILE_SUCCESS: return new Response(200,"Update ảnh đại diện thành công",data,type);
            case GET_IMGPROFILE_SUCCESS: return new Response(200,"Lấy ảnh đại diện thành công",data,type);
            case SEND_EMAIL_RENEW_PASSWORD_SUCCESS: return new Response(200,"Gửi yêu cầu thay đổi mật khẩu thành công, vui lòng nhấp vào đường dẫn trên email để hoàn tất quá trình đổi mật khẩu!",data,type);
            case RESET_PASSWORD_SUCCESS: return new Response(200,"Đổi mật khẩu thành công!",data,type);
            case GET_LIST_TIME_KEEPING_SUCCESS: return new Response(200,"Lấy danh sách chấm công thành công!",data,type);
        }
        return null;
    }
    public enum CodeDone
    {
        GET_INFOR_SUCCESS,
        LOGIN_SUCCESS,
        GET_DATA_SUCCESS,
        REMOVE_UID_SUCCESS,
        UPDATE_INFO_SUCCESS,
        UPDATE_IMGPROFILE_SUCCESS,
        GET_IMGPROFILE_SUCCESS,
        SEND_EMAIL_RENEW_PASSWORD_SUCCESS,
        RESET_PASSWORD_SUCCESS,
        GET_LIST_TIME_KEEPING_SUCCESS
    }
    public enum ErrorCode
    {
        USERNAME_EXIST,
        USERNAME_PASSWORD_FORMAT,
        EMAIL_EXIST,
        USER_UNVERIFY,
        USERNAME_FORMAT,
        USERNAME_LENGTH,
        EMAIL_ERROR,
        PHONE_ERROR,
        ADDRESS_ERROR,
        DATEOFBIRTH_ERROR,
        GENDER_ERROR,
        TIME_OUT,
        ACCOUNT_NOT_EXIST,
        UNKNOW_ERROR,
        PERMISSION_LIMIT,
        JOB_NULL,
        PERMISSION_ADMIN,
        UNAUTHORIZATION_TOKEN,
        NO_IMAGE,
        LINK_EXPIRED
        
    }
}
