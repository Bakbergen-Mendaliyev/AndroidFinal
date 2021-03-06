package com.example.chatproject.RecycleView.item

import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout
import com.example.chatproject.R
import com.example.chatproject.model.TextMessage
import com.google.firebase.auth.FirebaseAuth
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_text_message.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.wrapContent
import java.text.FieldPosition
import java.text.SimpleDateFormat

class TextMessageItem(val meessage: TextMessage,
val context: Context
): Item() {
    override fun bind(viewHolder: ViewHolder, position: Int){
        viewHolder.textView_message_text.text = meessage.text
        setTimeText(viewHolder)
        setMessageRootGravity(viewHolder)
    }

    private fun setTimeText(viewHolder: ViewHolder){
        val dateFormat = SimpleDateFormat
            .getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT)
        viewHolder.textView_message_time.text = dateFormat.format(meessage.time)
    }

    private fun setMessageRootGravity(viewHolder: ViewHolder){
        if( meessage.senderId == FirebaseAuth.getInstance().currentUser?.uid){
            viewHolder.message_root.apply {
                backgroundResource = R.drawable.rect_round_white
            val lParams = FrameLayout.LayoutParams(wrapContent, wrapContent, Gravity.END)
                this.layoutParams = lParams
            }
        }
        else{
            viewHolder.message_root.apply {
                backgroundResource = R.drawable.rect_round_primary_color
                val lParams = FrameLayout.LayoutParams(wrapContent, wrapContent, Gravity.START)
                this.layoutParams = lParams
            }
        }

    }
    override fun getLayout() = R.layout.item_text_message

    override fun isSameAs(other: com.xwray.groupie.Item<*>?): Boolean {
        if (other !is TextMessageItem)
            return false
        if(this.meessage != other.meessage)
            return false
        return true
    }

    override fun equals(other: Any?): Boolean {
       return isSameAs(other as? TextMessageItem)
    }
}