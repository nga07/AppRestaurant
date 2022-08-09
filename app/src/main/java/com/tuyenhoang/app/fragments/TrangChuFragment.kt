package com.tuyenhoang.app.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.tuyenhoang.app.R
import com.tuyenhoang.app.activity.TrangChuActivity
import com.tuyenhoang.app.adapter.DoanhThuAdapterRecycleView
import com.tuyenhoang.app.adapter.LoaiMonAdapterRecycleView
import com.tuyenhoang.app.helper.DonHangHelper
import com.tuyenhoang.app.helper.LoaiMonHelper
import com.tuyenhoang.app.model.DonHang
import com.tuyenhoang.app.model.LoaiMon
import java.text.SimpleDateFormat
import java.util.*

class TrangChuFragment : Fragment(), View.OnClickListener{

    private var rcv_displayhome_LoaiMon: RecyclerView? = null
    private var rcv_displayhome_DonTrongNgay: RecyclerView? = null
    private var layout_displayhome_ThongKe: RelativeLayout? = null
    private var layout_displayhome_XemBan: RelativeLayout? = null
    private var layout_displayhome_XemMenu: RelativeLayout? = null
    private var layout_displayhome_XemNV: RelativeLayout? = null
    private var txt_displayhome_ViewAllCategory: TextView? = null
    private var txt_displayhome_ViewAllStatistic: TextView? = null
    private var loaiMonHelper: LoaiMonHelper? = null
    private var donHangHelper: DonHangHelper? = null
    private var loaiMonList: List<LoaiMon>? = null
    private var donHangs: List<DonHang>? = null
    private var loaiMonAdapterRecycleView: LoaiMonAdapterRecycleView? = null
    private var doanhThuAdapterRecycleView: DoanhThuAdapterRecycleView? = null
    private var maquyen = 0
    private var manhanvien = 0
    private var textviewNhanVien: TextView? = null

    private var button:FloatingActionButton?=null
    private var buttonTrangChu:FloatingActionButton?=null
    private var buttonDoanhThu:FloatingActionButton?=null
    private var buttonBanAn:FloatingActionButton?=null
    private var buttonLoaiMonAn:FloatingActionButton?=null
    private var buttonNhanVien:FloatingActionButton?=null
    private var textTrangChu:TextView?=null
    private var textDoanhThu:TextView?=null
    private var textBanAn:TextView?=null
    private var textLoaiMonAn:TextView?=null
    private var textNhanVien:TextView?=null
    private var layoutContent:ConstraintLayout?=null
    private var allVisible:Boolean?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.trangchu_layout, container, false)
        (activity as TrangChuActivity?)!!.supportActionBar!!.title = "Trang chủ"
        setHasOptionsMenu(true)

        button=view.findViewById(R.id.button)
        buttonTrangChu=view.findViewById(R.id.home)
        buttonDoanhThu=view.findViewById(R.id.doanh_thu)
        buttonBanAn=view.findViewById(R.id.ban_an)
        buttonLoaiMonAn=view.findViewById(R.id.loai_mon_an)
        buttonNhanVien=view.findViewById(R.id.nhan_vien)
        textTrangChu=view.findViewById(R.id.text_home)
        textDoanhThu=view.findViewById(R.id.text_doanh_thu)
        textBanAn=view.findViewById(R.id.text_ban_an)
        textLoaiMonAn=view.findViewById(R.id.text_loai_mon_an)
        textNhanVien=view.findViewById(R.id.text_nhan_vien)
        layoutContent=view.findViewById(R.id.content)
        buttonTrangChu!!.visibility=View.GONE
        textTrangChu!!.visibility=View.GONE
        buttonDoanhThu!!.visibility=View.GONE
        textDoanhThu!!.visibility=View.GONE
        buttonBanAn!!.visibility=View.GONE
        textBanAn!!.visibility=View.GONE
        buttonLoaiMonAn!!.visibility=View.GONE
        textLoaiMonAn!!.visibility=View.GONE
        buttonNhanVien!!.visibility=View.GONE
        textNhanVien!!.visibility=View.GONE
        allVisible=false


        rcv_displayhome_LoaiMon = view.findViewById(R.id.rcv_displayhome_LoaiMon)
        rcv_displayhome_DonTrongNgay = view.findViewById(R.id.rcv_displayhome_DonTrongNgay)
        layout_displayhome_ThongKe = view.findViewById(R.id.layout_displayhome_ThongKe)
        layout_displayhome_XemBan = view.findViewById(R.id.layout_displayhome_XemBan)
        layout_displayhome_XemMenu = view.findViewById(R.id.layout_displayhome_XemMenu)
        layout_displayhome_XemNV = view.findViewById(R.id.layout_displayhome_XemNV)
        txt_displayhome_ViewAllCategory = view.findViewById(R.id.txt_displayhome_ViewAllCategory)
        txt_displayhome_ViewAllStatistic = view.findViewById(R.id.txt_displayhome_ViewAllStatistic)
        textviewNhanVien = view.findViewById(R.id.tv_nhanvien)
        loaiMonHelper = LoaiMonHelper()
        donHangHelper = DonHangHelper()
        maquyen = requireArguments().getInt("maquyen")
        manhanvien = requireArguments().getInt("manv")
        HienThiDSLoai()
        HienThiDonTrongNgay()
        if (maquyen == 2) {
            layout_displayhome_XemNV!!.visibility = View.GONE
            textviewNhanVien!!.visibility = View.GONE
        }
        layout_displayhome_ThongKe!!.setOnClickListener(this)
        layout_displayhome_XemBan!!.setOnClickListener(this)
        layout_displayhome_XemMenu!!.setOnClickListener(this)
        layout_displayhome_XemNV!!.setOnClickListener(this)
        txt_displayhome_ViewAllCategory!!.setOnClickListener(this)
        txt_displayhome_ViewAllStatistic!!.setOnClickListener(this)

        layoutContent!!.setOnClickListener(this)
        button!!.setOnClickListener(this)
        buttonTrangChu!!.setOnClickListener(this)
        buttonDoanhThu!!.setOnClickListener(this)
        buttonBanAn!!.setOnClickListener(this)
        buttonLoaiMonAn!!.setOnClickListener(this)
        buttonNhanVien!!.setOnClickListener(this)
        textTrangChu!!.setOnClickListener(this)
        textDoanhThu!!.setOnClickListener(this)
        textBanAn!!.setOnClickListener(this)
        textLoaiMonAn!!.setOnClickListener(this)
        textNhanVien!!.setOnClickListener(this)
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun HienThiDSLoai() {
        rcv_displayhome_LoaiMon!!.setHasFixedSize(true)
        rcv_displayhome_LoaiMon!!.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        loaiMonList = loaiMonHelper!!.getAllLoaiMon()
        loaiMonAdapterRecycleView =
            activity?.let {
                LoaiMonAdapterRecycleView(it, R.layout.icon_loai_mon_an, loaiMonList!!)
            }
        rcv_displayhome_LoaiMon!!.adapter = loaiMonAdapterRecycleView
        loaiMonAdapterRecycleView!!.notifyDataSetChanged()
    }

    @SuppressLint("SimpleDateFormat", "NotifyDataSetChanged")
    private fun HienThiDonTrongNgay() {
        rcv_displayhome_DonTrongNgay!!.setHasFixedSize(true)
        rcv_displayhome_DonTrongNgay!!.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        val ngaydat = dateFormat.format(calendar.time)
        donHangs = donHangHelper!!.getDonHangToId(manhanvien)
        doanhThuAdapterRecycleView =
            activity?.let {
                DoanhThuAdapterRecycleView(it, R.layout.icon_don_hang, donHangs!!)
            }
        rcv_displayhome_DonTrongNgay!!.adapter = doanhThuAdapterRecycleView
        loaiMonAdapterRecycleView!!.notifyDataSetChanged()
    }

    override fun onClick(v: View) {

        val id = v.id
        val navigationView =
            requireActivity().findViewById<View>(R.id.navigation_view_trangchu) as NavigationView
        when (id) {
            R.id.button ->{
                allVisible=if (!allVisible!!){
                    buttonTrangChu!!.show()
                    buttonDoanhThu!!.show()
                    buttonBanAn!!.show()
                    buttonLoaiMonAn!!.show()
                    if(maquyen==1){
                        buttonNhanVien!!.show()
                    }
                    textTrangChu!!.visibility=View.VISIBLE
                    textDoanhThu!!.visibility=View.VISIBLE
                    textBanAn!!.visibility=View.VISIBLE
                    textLoaiMonAn!!.visibility=View.VISIBLE
                    if(maquyen==1){
                        textNhanVien!!.visibility=View.VISIBLE
                    }
                    true
                }else{
                    buttonTrangChu!!.hide()
                    buttonDoanhThu!!.hide()
                    buttonBanAn!!.hide()
                    buttonLoaiMonAn!!.hide()
                    if(maquyen==1){
                        buttonNhanVien!!.hide()
                    }
                    textTrangChu!!.visibility=View.GONE
                    textDoanhThu!!.visibility=View.GONE
                    textBanAn!!.visibility=View.GONE
                    textLoaiMonAn!!.visibility=View.GONE
                    if(maquyen==1){
                        textNhanVien!!.visibility=View.GONE
                    }
                    false
                }
            }
            R.id.content->{
                buttonTrangChu!!.hide()
                buttonDoanhThu!!.hide()
                buttonBanAn!!.hide()
                buttonLoaiMonAn!!.hide()
                if(maquyen==1){
                    buttonNhanVien!!.hide()
                }
                textTrangChu!!.visibility=View.GONE
                textDoanhThu!!.visibility=View.GONE
                textBanAn!!.visibility=View.GONE
                textLoaiMonAn!!.visibility=View.GONE
                if(maquyen==1){
                    textNhanVien!!.visibility=View.GONE
                }
                allVisible=false
            }
            R.id.layout_displayhome_ThongKe, R.id.txt_displayhome_ViewAllStatistic,R.id.doanh_thu,R.id.text_doanh_thu -> {
                val bundle = Bundle()
                bundle.putInt("maquyen", maquyen)
                bundle.putInt("manv", manhanvien)
                val tranDisplayStatistic =
                    requireActivity().supportFragmentManager.beginTransaction()
                val donHangFragment = DonHangFragment()
                donHangFragment.arguments = bundle
                tranDisplayStatistic.replace(R.id.contentView, donHangFragment)
                tranDisplayStatistic.addToBackStack(null)
                tranDisplayStatistic.commit()
                navigationView.setCheckedItem(R.id.nav_statistic)
            }
            R.id.txt_displayhome_ViewAllCategory, R.id.layout_displayhome_XemMenu,R.id.loai_mon_an,R.id.text_loai_mon_an -> {
                val tranDisplayCategory =
                    requireActivity().supportFragmentManager.beginTransaction()
                tranDisplayCategory.replace(R.id.contentView, LoaiMonFragment())
                tranDisplayCategory.addToBackStack(null)
                tranDisplayCategory.commit()
                navigationView.setCheckedItem(R.id.nav_category)
            }
            R.id.layout_displayhome_XemBan,R.id.ban_an,R.id.text_ban_an -> {
                val tranDisplayStatistic =
                    requireActivity().supportFragmentManager.beginTransaction()
                tranDisplayStatistic.replace(R.id.contentView, BanAnFragment())
                tranDisplayStatistic.addToBackStack(null)
                tranDisplayStatistic.commit()
                navigationView.setCheckedItem(R.id.nav_table)
            }
            R.id.layout_displayhome_XemNV,R.id.nhan_vien,R.id.text_nhan_vien -> {
                val tranDisplayStatistic =
                    requireActivity().supportFragmentManager.beginTransaction()
                tranDisplayStatistic.replace(R.id.contentView, NhanVienFragment())
                tranDisplayStatistic.addToBackStack(null)
                tranDisplayStatistic.commit()
                navigationView.setCheckedItem(R.id.nav_staff)
            }
        }
    }
}