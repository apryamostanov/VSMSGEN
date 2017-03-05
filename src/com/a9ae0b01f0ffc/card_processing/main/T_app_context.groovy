package com.a9ae0b01f0ffc.card_processing.main


import com.a9ae0b01f0ffc.black_box.main.T_s
import com.a9ae0b01f0ffc.commons.ioc.T_class_loader
import com.a9ae0b01f0ffc.commons.main.T_common_context

class T_app_context {

    protected static ThreadLocal<T_app_context> p_context_thread_local = new ThreadLocal<T_app_context>()
    private T_class_loader p_ioc = T_app_const.GC_NULL_OBJ_REF as T_class_loader
    private T_app_commons p_commons = T_app_const.GC_NULL_OBJ_REF as T_app_commons
    private T_trxn_config p_trxn_config = T_app_const.GC_NULL_OBJ_REF as T_trxn_config

    static {
        p_context_thread_local.set(new T_app_context())
    }

    void init_custom(String i_commons_conf_file_name) {
        p_context_thread_local.get().p_commons = new T_app_commons(i_commons_conf_file_name)
        p_context_thread_local.get().p_trxn_config = new T_trxn_config(T_app_s.c().GC_TRXN_CONFIG_FILE)
        p_context_thread_local.get().p_ioc = new T_class_loader(T_app_s.c().GC_SMS_CLASSES_CONF)
        T_common_context.get_context().init_custom(i_commons_conf_file_name)
        T_s.x().init_custom(T_app_s.c().GC_BLACK_BOX_CONFIG)
    }

    T_class_loader get_ioc() {
        return p_context_thread_local.get().p_ioc
    }

    T_app_commons get_commons() {
        return ((T_app_context) p_context_thread_local.get()).p_commons
    }

    static T_app_context get_context() {
        return p_context_thread_local.get()
    }

    T_trxn_config get_trxn_config() {
        return ((T_app_context) p_context_thread_local.get()).p_trxn_config
    }

}
