package com.jun.jokedaquan.business.main.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jun.jokedaquan.business.main.fragments.AFragment;
import com.jun.jokedaquan.business.main.fragments.BFragment;
import com.jun.jokedaquan.business.main.fragments.CFragment;
import com.jun.jokedaquan.business.main.fragments.DFragment;
import com.jun.jokedaquan.business.main.fragments.EFragment;
import com.jun.jokedaquan.business.main.fragments.FFragment;
import com.jun.jokedaquan.business.main.fragments.GFragment;

/**
 * MainFragmentPagerAdapter
 * Created by Tse on 2016/10/29.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    public enum PagerType {
        A(0),
        B(1),
        C(2),
        D(3),
        E(4),
        F(5),
        G(6);
        private int value;

        PagerType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public static PagerType toPagerType(int value) {
            switch (value) {
                case 0:
                    return A;
                case 1:
                    return B;
                case 2:
                    return C;
                case 3:
                    return D;
                case 4:
                    return E;
                case 5:
                    return F;
                case 6:
                    return G;
                default:
                    return null;
            }
        }
    }

    public PagerType positionToType(int position) {
        switch (position) {
            default:
            case 0:
                return PagerType.A;
            case 1:
                return PagerType.B;
            case 2:
                return PagerType.C;
            case 3:
                return PagerType.D;
            case 4:
                return PagerType.E;
            case 5:
                return PagerType.F;
            case 6:
                return PagerType.G;
        }
    }

    public int typeToPosition(PagerType type) {
        switch (type) {
            case A:
                return 0;
            case B:
                return 1;
            case C:
                return 2;
            case D:
                return 3;
            case E:
                return 4;
            case F:
                return 5;
            case G:
                return 6;
            default:
                return -1;
        }
    }

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (positionToType(position)) {
            default:
            case A:
                return new AFragment();
            case B:
                return new BFragment();
            case C:
                return new CFragment();
            case D:
                return new DFragment();
            case E:
                return new EFragment();
            case F:
                return new FFragment();
            case G:
                return new GFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            default:
            case 0:
                return AFragment.getPageTitle();
            case 1:
                return BFragment.getPageTitle();
            case 2:
                return CFragment.getPageTitle();
            case 3:
                return DFragment.getPageTitle();
            case 4:
                return EFragment.getPageTitle();
            case 5:
                return FFragment.getPageTitle();
            case 6:
                return GFragment.getPageTitle();
        }
    }
}
