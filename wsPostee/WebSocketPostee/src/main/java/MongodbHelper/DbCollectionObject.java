/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongodbHelper;

import AuthClient.Signup.PostData;
import AuthClient.Signup.ServerPutRegister;

/**
 *
 * @author Hoang Quoc Bao
 */
public class DbCollectionObject extends ServerPutRegister {
    public String _id;

    public DbCollectionObject(PostData data) {
        super(data);
    }
}
