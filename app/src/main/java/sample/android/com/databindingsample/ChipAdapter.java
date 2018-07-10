package sample.android.com.databindingsample;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.chip.Chip;
import android.support.design.resources.TextAppearance;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.List;

/**
 * Created by akhil on 29/6/18.
 */

public class ChipAdapter extends RecyclerView.Adapter<ChipAdapter.ViewHolder> {

    private List<String> chipNames;
    private Context context;

    public ChipAdapter(List<String> chipNames, Context context) {
        this.chipNames = chipNames;
        this.context = context;
    }

    @NonNull
    @Override
    public ChipAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chips, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChipAdapter.ViewHolder holder, int position) {
        holder.updateUi(position);
    }

    @Override
    public int getItemCount() {
        return chipNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
        //  private com.robertlevonyan.views.chip.Chip chip;
        private Chip chip;
        private boolean isChecked = false;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chip);
            chip.setCheckable(true);
            chip.setChipIconEnabled(false);
            chip.setOnClickListener(this);
            chip.setCheckedIconEnabled(false);
            chip.setOnCheckedChangeListener(this);
        }

        private void updateUi(int position) {

            chip.setChipText(chipNames.get(position));
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b) {
                chip.setChipBackgroundColorResource(R.color.colorPrimary);
            } else {
                chip.setChipBackgroundColorResource(R.color.gray);
            }
        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
               /* case R.id.chip:
                    isChecked = true;
                    if(isChecked)
                    {
                        chip.setChipBackgroundColorResource(R.color.colorPrimary);
                        isChecked = false;
                    }
                    else
                    {
                        chip.setChipBackgroundColorResource(R.color.gray);
                    }
                    break;*/
            }
        }
    }
}
